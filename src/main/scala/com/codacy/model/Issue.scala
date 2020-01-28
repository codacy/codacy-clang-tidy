package com.codacy.model

import java.nio.file.Path

import com.codacy.model.Issue.LineLocation
import com.codacy.plugins.api.results

final case class Issue(
    patternId: results.Pattern.Id,
    filename: Path,
    message: Issue.Message,
    level: results.Result.Level,
    category: Option[results.Pattern.Category],
    location: LineLocation
)

object Issue {
  case class Message(text: String)
  case class LineLocation(line: Int)
}
