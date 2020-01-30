package com.codacy.clangtidy
import java.nio.file.Path

import com.codacy.model.IssueResult
import com.codacy.plugins.api.results

case class ClangTidyResult(path: Path, line: Int, column: Int, level: String, message: String, check: String)

object ClangTidyResult {

  def toIssue(result: ClangTidyResult): IssueResult = {
    IssueResult.Issue(
      results.Pattern.Id(result.check),
      result.path,
      IssueResult.Issue.Message(result.message),
      ClangTidyResult.convertLevel(result.level),
      ClangTidyResult.convertCategory(result.check),
      IssueResult.Issue.FullLocation(result.line, result.column)
    )
  }

  private def convertLevel(level: String): results.Result.Level.Value = level match {
    case "error" => results.Result.Level.Err
    case "warning" => results.Result.Level.Warn
    case "note" => results.Result.Level.Info
    case _ => results.Result.Level.Info
  }

  private def convertCategory(checkName: String): Option[results.Pattern.Category] =
    None
}
