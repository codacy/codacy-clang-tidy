package com.codacy.model

import java.nio.file.Path

final case class ToolResults(tool: String, issues: IssuesAnalysis)

final case class IssuesAnalysis(results: Set[FileResults])

final case class FileResults(filename: Path, results: Set[Issue])
