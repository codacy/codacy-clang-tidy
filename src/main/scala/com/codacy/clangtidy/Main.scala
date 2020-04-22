package com.codacy.clangtidy

import java.nio.file.Paths

object Main {

  def main(args: Array[String]): Unit = {
    // TODO make this dynamic
    val toolName = "clang-tidy"

    // TODO allow to read report from file
    val lines = scala.io.Source.stdin.getLines().to(LazyList)
    val pwd = Paths.get(System.getProperty("user.dir"))
    val jsonString = new Converter(toolName).convert(lines, relativizeTo = pwd)
    println(jsonString)
  }

}
