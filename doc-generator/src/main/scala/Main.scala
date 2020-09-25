import com.codacy.plugins.api.results.Pattern.{Description, DescriptionText, Subcategory}
import com.codacy.plugins.api.results.Result.Level
import com.codacy.plugins.api.results.{Pattern, Tool}

import play.api.libs.json.Json
import scala.sys.process._
import better.files.Dsl._

import scala.sys.process._

object Main extends App {

  val llvmVersion = "10.0.1"

  val toolName = "clang-tidy"

  def toInputStream(s: String) =
    new java.io.ByteArrayInputStream(s.getBytes(java.nio.charset.StandardCharsets.UTF_8.name))

  val targetDir = pwd / "doc-generator" / "target"
  val llvmProjectDir = targetDir / "llvm-project"
  val clangExtraDir = llvmProjectDir / "clang-tools-extra"

  val llvmBranch = s"llvmorg-$llvmVersion"

  if (!clangExtraDir.exists) {
    val pb =
      Process(
        command =
          Seq("git", "clone", "--depth", "1", "--branch", llvmBranch, "https://github.com/llvm/llvm-project.git"),
        cwd = Some(targetDir.toJava)
      )
    require(pb.! == 0, "git clone failed")
  } else {
    val fetchProcess =
      Process(command = Seq("git", "fetch", "origin", "--depth", "1", llvmBranch), cwd = Some(clangExtraDir.toJava))
    val resetProcess = Process(command = Seq("git", "reset", "--hard", "FETCH_HEAD"), cwd = Some(clangExtraDir.toJava))
    require(fetchProcess.! == 0, "git pull failed")
    require(resetProcess.! == 0, "git reset failed")
  }

  /**
    * Clang Tidy documentation contains relative links that link to
    * other documentation files. We are making these links absolute
    * prepending them with the clang tidy documentation website.
    *
    * @param s A String containing Commonmark documentation
    * @return A String with Links relative to Clang tidy docs
    */
  def fixRepoLinks(s: String): String = {
    val linkRegex = """\[(.+)\]\((.+\.html)\)""".r("name", "link")
    linkRegex.replaceAllIn(s, matching => {
      val name = matching.group("name")
      val link = matching.group("link")
      val linkToClangTidy =
        if (link.matches("https?:\\/\\/.+")) link
        else s"https://clang.llvm.org/extra/clang-tidy/checks/${link.stripPrefix("/")}"
      val linkEscaped = linkToClangTidy.replaceAllLiterally(" ", "").trim
      val nameEscaped = name.trim
      s"[$nameEscaped]($linkEscaped)"
    })
  }

  val patternsWithDocs: Seq[(String, String)] = {
    val iterator = for {
      file <- (clangExtraDir / "docs" / "clang-tidy" / "checks").children
      patternId = file.nameWithoutExtension(includeAll = false)
      if file.extension.exists(_ == ".rst") && file.nameWithoutExtension != "list"
      markdownFile = Seq("pandoc", "-t", "commonmark", file.pathAsString).!!
      content = markdownFile.linesIterator.drop(2).mkString(System.lineSeparator())
      withRepoLinks = fixRepoLinks(content)
    } yield (patternId, withRepoLinks)
    iterator.toSeq
  }

  def categoryFromPatternId(patternId: String): (Pattern.Category, Option[Subcategory]) = patternId match {
    case s"android-$_" => (Pattern.Category.Security, Some(Pattern.Subcategory.Android))
    case "cert-err52-cpp" => (Pattern.Category.Security, Some(Pattern.Subcategory.DoS))
    case s"clang-analyzer-security.insecureAPI$_" =>
      (Pattern.Category.Security, Some(Pattern.Subcategory.InsecureModulesLibraries))
    case "clang-analyzer-security" => (Pattern.Category.Security, None)
    case s"${_}unused$_" => (Pattern.Category.UnusedCode, None)
    case "misc-unused-alias-decls" => (Pattern.Category.UnusedCode, None)
    case s"bugprone-$_" => (Pattern.Category.ErrorProne, None)
    case s"readability-$_" => (Pattern.Category.CodeStyle, None)
    case s"performance-$_" => (Pattern.Category.Performance, None)
    case s"portability-$_" => (Pattern.Category.Compatibility, None)
    case _ => (Pattern.Category.ErrorProne, None)
  }

  def withoutFamily(patternId: String): String = {
    Seq(
      "bugprone",
      "cppcoreguidelines",
      "clang-analyzer-cplusplus",
      "clang-analyzer-core",
      "clang-analyzer-osx",
      "hicpp",
      "misc",
      "modernize",
      "objc",
      "performance",
      "portability",
      "readability",
      "zircon"
    ).map(s => s"$s-").foldLeft(patternId)((acc, prefix) => acc.stripPrefix(prefix))
  }

  val patterns = patternsWithDocs.map {
    case (patternId, _) =>
      // Since level will be sent in the report
      // we are using a default one here
      val level = Level.Warn
      val (category, subcategory) = categoryFromPatternId(patternId)
      Pattern.Specification(Pattern.Id(patternId), level, category, subcategory, Set.empty)
  }

  val specification = Tool.Specification(Tool.Name(toolName), Some(Tool.Version(llvmVersion)), patterns.toSet)

  def removeHtmlTags(s: String): String = {
    s.replaceAll("""<[^>]*>""", "")
  }

  val descriptions: Seq[Description] = patternsWithDocs.map {
    case (patternId, doc) =>
      val descriptionText = {
        val firstPeriod = doc.linesIterator.drop(2).mkString(" ").split("\\. ").head.stripSuffix(".")
        val withoutLinks = removeHtmlTags(firstPeriod.replaceAll("""\[(.*)\]\(.*\)""", "$1"))
        Some(DescriptionText(withoutLinks))
      }

      Description(
        patternId = Pattern.Id(patternId),
        title = Pattern.Title(withoutFamily(patternId).split("[-\\.]").mkString(" ").capitalize),
        description = descriptionText,
        timeToFix = None,
        parameters = Set.empty
      )
  }

  val specificationJsonString = Json.prettyPrint(Json.toJson(specification))
  val descriptionsJsonString = Json.prettyPrint(Json.toJson(descriptions))

  val docsDir = pwd / "docs"
  mkdirs(docsDir)
  val descriptionDir = docsDir / "description"
  rm(descriptionDir)
  mkdirs(descriptionDir)

  for ((pattern, markdown) <- patternsWithDocs)
    (descriptionDir / s"$pattern.md").writeText(markdown + System.lineSeparator)

  val patternJsonFile = docsDir / "patterns.json"
  patternJsonFile.writeText(specificationJsonString + System.lineSeparator)

  val descriptionJsonFile = docsDir / "description" / "description.json"
  descriptionJsonFile.writeText(descriptionsJsonString + System.lineSeparator)
}
