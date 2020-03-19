package com.codacy.clangtidy
import java.nio.file.Path

import com.codacy.plugins.api.results
import com.codacy.analysis.core.model.{FullLocation, Issue}

case class ClangTidyResult(path: Path, line: Int, column: Int, level: String, message: String, check: String)

object ClangTidyResult {

  def toIssue(result: ClangTidyResult): Issue = {
    Issue(
      results.Pattern.Id(result.check),
      result.path,
      Issue.Message(result.message),
      ClangTidyResult.convertLevel(result.level),
      ClangTidyResult.convertCategory(result.check),
      FullLocation(result.line, result.column)
    )
  }

  private def convertLevel(level: String): results.Result.Level.Value = level match {
    case "error" => results.Result.Level.Err
    case "warning" => results.Result.Level.Warn
    case "note" => results.Result.Level.Info
    case _ => results.Result.Level.Info
  }

  // TODO use patterns.json to get the category CY-357
  private def convertCategory(checkName: String): Option[results.Pattern.Category] =
    None
}
