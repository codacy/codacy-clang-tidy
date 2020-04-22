package com.codacy.clangtidy

import com.codacy.analysis.core.model.{IssuesAnalysis, ToolResults}
import com.codacy.analysis.core.model.IssuesAnalysis.FileResults
import com.codacy.analysis.core.serializer.IssuesReportSerializer
import java.nio.file.Path

class Converter(toolName: String) {

  def convert(lines: Seq[String], relativizeTo: Path): String = {
    val parsed = ClangTidyReportParser.parse(lines, relativizeTo)

    val grouped = parsed
      .groupBy(_.path)
      .view
      .map {
        case (path, res) =>
          FileResults(path, res.view.map(ClangTidyResult.toIssue).to(Set))
      }
      .to(Set)

    val toolResults = ToolResults(toolName, IssuesAnalysis.Success(grouped))
    IssuesReportSerializer.toJsonString(Set(toolResults))
  }

}
