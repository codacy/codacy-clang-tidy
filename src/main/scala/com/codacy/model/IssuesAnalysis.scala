package com.codacy.model

import java.nio.file.Path

final case class ToolResults(tool: String, issues: IssuesAnalysis)

sealed trait IssuesAnalysis

object IssuesAnalysis {
  final case class Success(results: Set[FileResults]) extends IssuesAnalysis
}

final case class FileResults(filename: Path, results: Set[IssueResult])
