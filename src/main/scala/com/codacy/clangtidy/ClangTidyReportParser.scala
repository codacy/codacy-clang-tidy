package com.codacy.clangtidy

import java.nio.file.{Path, Paths}

object ClangTidyReportParser {

  private val ResultRegex =
    raw"((?>[[:alpha:]]:\\)?[^:]+):(\d+):(\d+):\s?([^:]+)\s?:\s?(.+)\s\[([^\]]+)\]".r

  def parse(lines: Seq[String], relativizeTo: Path): Seq[ClangTidyResult] = {
    lines.flatMap {
      case result @ ResultRegex(pathStr, line, column, level, txt, checksList) =>
        val path = relativizeTo.relativize(Paths.get(pathStr).toAbsolutePath())
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
