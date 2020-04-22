package com.codacy.clangtidy

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import java.nio.file.{ Path, Paths }

class ConverterSpecs extends AnyWordSpec with Matchers {
  implicit val pwd: Path = Paths.get("/src")

  "Converter::convert" should {
    "convert output correctly" in {
      val lines =
        """Error while trying to load a compilation database:
          |Could not auto-detect compilation database for file "/src/main.c"
          |No compilation database found in /src or any parent directory
          |fixed-compilation-database: Error while opening fixed database: No such file or directory
          |json-compilation-database: Error while opening JSON database: No such file or directory
          |Running without flags.
          |18 warnings and 2 errors generated.
          |Error while processing /src/main.c.
          |/src/main.c:10:6: error: expected expression [clang-diagnostic-error]
          |  if() {
          |     ^
          |/src/main.c:13:3: error: do not use 'else' after 'return' [readability-else-after-return,-warnings-as-errors]
          |  else {
          |  ^~~~~~
          |/src/main.c:17:1: error: expected identifier or '(' [clang-diagnostic-error]
          |--store-check-profile=
          |^
          |Suppressed 17 warnings (17 in non-user code).
          |Use -header-filter=.* to display errors from all non-system headers. Use -system-headers to display errors from system headers as well.
          |1 warning treated as error
          |""".stripMargin.split("\n").toSeq

      val toolName = "definitely-not-clang-tidy"

      val expected =
        s"""[{"tool":"$toolName","issues":{"Success":{"results":[{"filename":"main.c","results":[{"Issue":{"patternId":{"value":"ClangTidy_clang-diagnostic-error"},"filename":"main.c","message":{"text":"expected expression"},"level":"Error","location":{"FullLocation":{"line":10,"column":6}}}},{"Issue":{"patternId":{"value":"ClangTidy_readability-else-after-return"},"filename":"main.c","message":{"text":"do not use 'else' after 'return'"},"level":"Error","location":{"FullLocation":{"line":13,"column":3}}}},{"Issue":{"patternId":{"value":"ClangTidy_clang-diagnostic-error"},"filename":"main.c","message":{"text":"expected identifier or '('"},"level":"Error","location":{"FullLocation":{"line":17,"column":1}}}}]}]}}}]"""

      new Converter(toolName).convert(lines) should be(expected)
    }
  }

}
