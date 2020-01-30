package com.codacy.clangtidy

import java.nio.file.Path

import com.codacy.model.IssueResult.Issue
import com.codacy.model.{FileResults, IssueResult, IssuesAnalysis, ToolResults}
import com.codacy.plugins.api.results
import io.circe.generic.semiauto._
import io.circe.syntax._
import io.circe.{Encoder, Printer}

object CodacyReportSerializer {
  private implicit val levelEncoder: Encoder[com.codacy.plugins.api.results.Result.Level.Value] =
    Encoder.encodeEnumeration(com.codacy.plugins.api.results.Result.Level)

  private implicit val categoryEncoder: Encoder[com.codacy.plugins.api.results.Pattern.Category.Value] =
    Encoder.encodeEnumeration(com.codacy.plugins.api.results.Pattern.Category)

  private implicit val pathEncoder: Encoder[Path] = Encoder[String].contramap(_.toString)

  private implicit val toolResultsEncoder: Encoder[ToolResults] = deriveEncoder[ToolResults]
  private implicit val issuesAnalysisEncoder: Encoder[IssuesAnalysis] = deriveEncoder[IssuesAnalysis]
  private implicit val fileResultsEncoder: Encoder[FileResults] = deriveEncoder[FileResults]
  private implicit val issueResultEncoder: Encoder[IssueResult] = deriveEncoder[IssueResult]
  private implicit val patternIdEncoder: Encoder[results.Pattern.Id] = deriveEncoder[results.Pattern.Id]
  private implicit val issueMessageEncoder: Encoder[Issue.Message] = deriveEncoder[Issue.Message]
  private implicit val issueLocationEncoder: Encoder[Issue.Location] = deriveEncoder[Issue.Location]

  def toJsonString(toolResults: Set[ToolResults]): String =
    toolResults.asJson.printWith(Printer.noSpaces.copy(dropNullValues = true))
}
