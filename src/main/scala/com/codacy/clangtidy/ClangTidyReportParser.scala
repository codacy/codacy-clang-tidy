package com.codacy.clangtidy

import java.nio.file.{Path, Paths}

object ClangTidyReportParser {

  private val ResultRegex =
    "(.+|[a-zA-Z]:\\\\.+):([0-9]+):([0-9]+): ([^:]+): (.+) \\[(.+?)\\]".r

  def parse(lines: Seq[String])(implicit pwd: Path): Seq[ClangTidyResult] = {
    lines.flatMap {
      case result @ ResultRegex(pathStr, line, column, level, txt, checksList) =>
        val path = pwd.relativize(Paths.get(pathStr))
        val firstCheck = checksList.split(",").headOption

        firstCheck
          .map(check => ClangTidyResult(path, line.toInt, column.toInt, level, txt, check))
          .orElse {
            System.err.println(s"Result does not contain checks: '$result''")
            None
          }

      case _ => None
    }
  }
}
