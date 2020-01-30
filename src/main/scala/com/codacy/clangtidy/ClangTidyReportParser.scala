package com.codacy.clangtidy

import java.nio.file.Paths

object ClangTidyReportParser {

  private val ResultRegex =
    "(.+|[a-zA-Z]:\\\\.+):([0-9]+):([0-9]+): ([^:]+): (.+) \\[(.+?)\\]".r

  def parse(lines: Seq[String]): Seq[ClangTidyResult] = {
    lines.collect {
      case result @ ResultRegex(pathStr, line, column, level, txt, checksList) =>
        val path = Paths.get(pathStr)
        val check =
          checksList
            .split(",")
            .headOption
            .getOrElse(throw new Exception(s"Result does not contain checks: '$result''"))

        ClangTidyResult(path, line.toInt, column.toInt, level, txt, check)
    }
  }

}
