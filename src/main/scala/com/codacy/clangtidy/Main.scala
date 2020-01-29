package com.codacy.clangtidy

import io.circe.generic.AutoDerivation

object Main extends App with AutoDerivation {

  // TODO make this dynamic
  val toolName = "clang-tidy"

  // TODO allow to read report from file
  val lines = scala.io.Source.stdin.getLines().to(LazyList)
  val jsonString = new Converter(toolName).convert(lines)
  println(jsonString)

}
