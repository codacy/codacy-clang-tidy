package com.codacy.model

import java.nio.file.Path

import com.codacy.plugins.api.results

sealed trait IssueResult

object IssueResult {

  final case class Issue(
      patternId: results.Pattern.Id,
      filename: Path,
      message: Issue.Message,
      level: results.Result.Level,
      category: Option[results.Pattern.Category],
      location: Issue.Location
  ) extends IssueResult

  object Issue {

    sealed trait Location
    final case class FullLocation(line: Int, column: Int) extends Location

    case class Message(text: String)
  }
}
