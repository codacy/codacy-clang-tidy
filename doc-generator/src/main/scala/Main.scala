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

  val clangExtraDir = pwd / "clang-tools-extra"

  if (!(clangExtraDir).exists && clangExtraDir.nonEmpty) {
    Seq("git", "clone", "--depth", "1", "https://github.com/llvm-mirror/clang-tools-extra.git").!
  } /* else {
    Seq("git", "pull").!
  } */


  val patternsWithDocs = {
    val it = for {
      file <- (clangExtraDir / "docs" / "clang-tidy" / "checks").children.filter(_.extension.exists(_ == ".rst"))
      content = file.lines.tail.mkString(System.lineSeparator)
      converted = (Seq("pandoc", "-t", "markdown_strict") #< toInputStream(content)).!!
    } yield (file.nameWithoutExtension, converted)
    it.toSeq
  }

  val docsDir = mkdirs(pwd / "docs")
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
    case (patternId, doc) =>
      val (category, subcategory) = categoryFromPatternId(patternId)
      Pattern.Specification(Pattern.Id(patternId), Level.Info, category, subcategory, None)
  }

  val specification = Tool.Specification(Tool.Name("Clang Tidy"), Some(Tool.Version("10.0.0")), patterns.toSet)
  val specificationJson = Json.toJson(specification)

  val specificationJsonString = Json.prettyPrint(specificationJson)

  val patternJsonFile = docsDir / "patterns.json"
  patternJsonFile.writeText(specificationJsonString)
}
