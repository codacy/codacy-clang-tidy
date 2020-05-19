package com.codacy.clangtidy

import java.nio.file.Paths
import scala.io.Codec

case class Config(encoding: Codec = Codec.default)

object Main {
  val toolName = "clang-tidy"

  val parser = new scopt.OptionParser[Config](toolName) {
    opt[String]('e', "encoding")
      .action { (encoding, config) =>
        val newCodec = try {
          Codec(encoding)
        } catch {
          case e: java.nio.charset.UnsupportedCharsetException =>
            System.err.println(s"Unsupported encoding: $encoding")
            throw e
        }
        config.copy(encoding = newCodec)
      }
      .text("input encoding (default: UTF-8)")
  }

  def main(args: Array[String]): Unit = {
    parser.parse(args, Config()) match {
      case Some(config) =>
        // TODO allow to read report from file
        val stdin = scala.io.Source.fromInputStream(System.in)(config.encoding)
        val lines = stdin.getLines().to(LazyList)
        val pwd = Paths.get(System.getProperty("user.dir"))
        val jsonString = new Converter(toolName).convert(lines, relativizeTo = pwd)
        println(jsonString)
      case None =>
        System.exit(1)
    }
  }

}
