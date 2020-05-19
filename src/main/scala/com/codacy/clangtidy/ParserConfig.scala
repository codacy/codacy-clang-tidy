package com.codacy.clangtidy

import scopt.OptionParser

import scala.io.Codec

case class ParserConfig(encoding: Codec = Codec.default)

object ParserConfig {

  def parse(toolName: String): OptionParser[ParserConfig] = new OptionParser[ParserConfig](toolName) {
    opt[String]('e', "encoding")
      .action { (encoding, config) =>
        config.copy(encoding = Codec(encoding))
      }
      .text(s"input encoding (default: ${ParserConfig().encoding.name})")
  }

  def withConfig(toolName: String, args: Array[String])(f: ParserConfig => Unit): Int = {
    val parser = this.parse(toolName)

    parser.parse(args, ParserConfig()) match {
      case Some(config) =>
        f(config)
        0
      case None =>
        1
    }
  }
}
