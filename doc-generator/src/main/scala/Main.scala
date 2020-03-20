import scala.sys.process._
import better.files.Dsl._
import com.codacy.plugins.api.results.Pattern
import com.codacy.plugins.api.results.Result.Level
import com.codacy.plugins.api.results.Tool
import play.api.libs.json.Json
import com.codacy.plugins.api.results.Pattern.Subcategory

object Main extends App {

  def toInputStream(s: String) =
    new java.io.ByteArrayInputStream(s.getBytes(java.nio.charset.StandardCharsets.UTF_8.name))

  val targetDir = pwd / "doc-generator" / "target"
  val clangExtraDir = targetDir / "clang-tools-extra"

  if (!(clangExtraDir).exists && clangExtraDir.nonEmpty) {
    val processBuilder = Process(command = Seq("git", "clone", "--depth", "1", "https://github.com/llvm-mirror/clang-tools-extra.git"), cwd = Some(targetDir.toJava))
    require(processBuilder.! == 0, "git clone failed")
  } /* else {
    Seq("git", "pull").!
  } */

  val patternsWithDocs: Seq[(String, String)] = {
    def isNotSeparator(s: String) = !s.startsWith("====")
    val iterator = for {
      file <- (clangExtraDir / "docs" / "clang-tidy" / "checks").children
      patternId = file.nameWithoutExtension(includeAll = false)
      if file.extension.exists(_ == ".rst") && file.nameWithoutExtension != "list"
      linesSeq = file.lines.toSeq
      content = linesSeq.dropWhile(isNotSeparator)
      toCovert = (patternId +: content).mkString(System.lineSeparator)
      converted = (Seq("pandoc", "-t", "markdown_strict") #< toInputStream(toCovert)).!!
    } yield (patternId, converted)
    iterator.toSeq
  }

  val docsDir = pwd / "docs"
  rm(docsDir)
  mkdirs(docsDir)
  val descriptionDir = mkdirs(docsDir / "description")

  for ((pattern, markdown) <- patternsWithDocs) (descriptionDir / s"$pattern.md").writeText(markdown)

  def categoryFromPatternId(patternId: String): (Pattern.Category, Option[Subcategory]) = patternId match {
    case s"android-$_" => (Pattern.Category.Security, Some(Pattern.Subcategory.Android))
    case "cert-err52-cpp" => (Pattern.Category.Security, Some(Pattern.Subcategory.DoS))
    case "misc-unused-alias-decls" => (Pattern.Category.UnusedCode, None)
    case s"bugprone-$_" => (Pattern.Category.ErrorProne, None)
    case s"readability-$_" => (Pattern.Category.Comprehensibility, None)
    case s"performance-$_" => (Pattern.Category.Performance, None)
    case s"portability-$_" => (Pattern.Category.Compatibility, None)
    case _ => (Pattern.Category.CodeStyle, None)
  }

  val patterns = patternsWithDocs.map {
    case (patternId, _) =>
      val (category, subcategory) = categoryFromPatternId(patternId)
      Pattern.Specification(Pattern.Id(patternId), Level.Info, category, subcategory, None)
  }

  val specification = Tool.Specification(Tool.Name("Clang Tidy"), Some(Tool.Version("10.0.0")), patterns.toSet)
  val specificationJson = Json.toJson(specification)

  val specificationJsonString = Json.prettyPrint(specificationJson)

  val patternJsonFile = docsDir / "patterns.json"
  patternJsonFile.writeText(specificationJsonString)
}
