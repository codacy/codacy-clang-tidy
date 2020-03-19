package com.codacy.clangtidy

import com.codacy.analysis.core.model.{IssuesAnalysis, ToolResults}
import com.codacy.analysis.core.model.IssuesAnalysis.FileResults
import com.codacy.analysis.core.serializer.IssuesReportSerializer

class Converter(toolName: String) {

  def convert(lines: Seq[String]): String = {
    val parsed = ClangTidyReportParser.parse(lines)

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
