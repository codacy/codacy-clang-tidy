package com.codacy.clangtidy

import java.nio.file.Paths

import caseapp.core.RemainingArgs
import caseapp.core.app.CaseApp

object Main extends CaseApp[ParserOptions] {
  val toolName = "clang-tidy"

  def run(options: ParserOptions, arg: RemainingArgs): Unit = {
    // TODO allow to read report from file
    val stdin = scala.io.Source.fromInputStream(System.in)(options.codecEncoding)
    val lines = stdin.getLines().to(LazyList)
    val pwd = Paths.get(System.getProperty("user.dir"))
    val jsonString = new Converter(toolName).convert(lines, relativizeTo = pwd)
    println(jsonString)
  }

}
