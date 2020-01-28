package com.codacy.clangtidy

import java.nio.file.Path

import com.codacy.model.ToolResults
import io.circe.generic.AutoDerivation
import io.circe.syntax._
import io.circe.{Encoder, Printer}

class ReportSerializer extends AutoDerivation {
  private implicit val levelEncoder: Encoder[com.codacy.plugins.api.results.Result.Level.Value] =
    Encoder.encodeEnumeration(com.codacy.plugins.api.results.Result.Level)

  private implicit val categoryEncoder: Encoder[com.codacy.plugins.api.results.Pattern.Category.Value] =
    Encoder.encodeEnumeration(com.codacy.plugins.api.results.Pattern.Category)

  private implicit val pathEncoder: Encoder[Path] = Encoder[String].contramap(_.toString)

  def toJsonString(toolResults: Set[ToolResults]): String =
    toolResults.asJson.printWith(Printer.noSpaces.copy(dropNullValues = true))
}
