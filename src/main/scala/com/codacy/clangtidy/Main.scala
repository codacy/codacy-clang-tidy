package com.codacy.clangtidy

import java.nio.file.Paths

object Main {
  val toolName = "clang-tidy"

  def run(config: ParserConfig): Unit = {
    val stdin = scala.io.Source.fromInputStream(System.in)(config.encoding)
    val lines = stdin.getLines().to(LazyList)
    val pwd = Paths.get(System.getProperty("user.dir"))
    val jsonString = new Converter(toolName).convert(lines, relativizeTo = pwd)
    println(jsonString)
  }

  def main(args: Array[String]): Unit = {
    val exitStatus = ParserConfig.withConfig(toolName, args) { config =>
      run(config)
    }
    sys.exit(exitStatus)
  }

}
