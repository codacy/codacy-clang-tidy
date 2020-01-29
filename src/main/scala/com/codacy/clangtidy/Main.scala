package com.codacy.clangtidy

import com.codacy.model.{FileResults, IssuesAnalysis, ToolResults}
import io.circe.generic.AutoDerivation

object Main extends App with AutoDerivation {

  val toolName = "clang-tidy"

  val lines = scala.io.Source.stdin.getLines().to(LazyList)
  val parsed = new ReportParser().parse(lines)

  val grouped = parsed
    .groupBy(_.path)
    .view
    .map {
      case (path, res) =>
        FileResults(path, res.view.map(ClangTidyResult.toIssue).to(Set))
    }
    .to(Set)

  val toolResults = ToolResults(toolName, IssuesAnalysis(grouped))
  val jsonString = new ReportSerializer().toJsonString(Set(toolResults))
  println(jsonString)

}
