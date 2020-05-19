package com.codacy.clangtidy

import caseapp._

import scala.io.Codec

@ProgName("codacy-clang-tidy")
case class ParserOptions(
    @Name("e") @ValueDescription("input encoding")
    encoding: Option[String]
) {

  def codecEncoding: Codec = {
    this.encoding match {
      case None => Codec.default
      case Some(enc) => Codec(enc)
    }
  }
}
