package com.codacy.clangtidy

import java.nio.file.Paths

import org.scalatest.{Matchers, WordSpec}

class ReportParserSpecs extends WordSpec with Matchers {

  "ReportParser::parse" should {
    "parse simple result" in {
      val line =
        "/src/main.c:10:6: error: expected expression [readability-else-after-return]"

      new ReportParser().parse(Seq(line)) should be(
        Seq(
          ClangTidyResult(
            Paths.get("/src/main.c"),
            10,
            6,
            "error",
            "expected expression",
            "readability-else-after-return"
          )
        )
      )
    }

    "parse result which contains multiple 'checks'" in {
      val line =
        "/src/main.c:15:3: error: do not use 'else' after 'return' [readability-else-after-return,-warnings-as-errors]"

      new ReportParser().parse(Seq(line)) should be(
        List(
          ClangTidyResult(
            Paths.get("/src/main.c"),
            15,
            3,
            "error",
            "do not use 'else' after 'return'",
            "readability-else-after-return"
          )
        )
      )
    }

    "parse multiple lines correctly" in {
      val line = Seq(
        "/src/main.c:17:1: error: expected identifier or '[' [clang-diagnostic-error]",
        "/src/main.c:15:3: error: do not use 'else' after 'return' [readability-else-after-return,-warnings-as-errors]"
      )

      val expected = Seq(
        ClangTidyResult(
          Paths.get("/src/main.c"),
          17,
          1,
          "error",
          "expected identifier or '['",
          "clang-diagnostic-error"
        ),
        ClangTidyResult(
          Paths.get("/src/main.c"),
          15,
          3,
          "error",
          "do not use 'else' after 'return'",
          "readability-else-after-return"
        )
      )

      new ReportParser().parse(line) should be(expected)
    }

    "ignore lines that don't match " in {
      val line = Seq(
        "/src/main.c:15:notANumber: error: do not use 'else' after 'return' [readability-else-after-return,-warnings-as-errors]",
        "/src/main.c:15:3: error: do not use 'else' after 'return' []",
        "Suppressed 17 warnings (17 in non-user code)."
      )

      new ReportParser().parse(line) should be(empty)
    }

    "parse a full output correctly" in {
      val output =
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
          |""".stripMargin

      val expected = Seq(
        ClangTidyResult(Paths.get("/src/main.c"), 10, 6, "error", "expected expression", "clang-diagnostic-error"),
        ClangTidyResult(
          Paths.get("/src/main.c"),
          13,
          3,
          "error",
          "do not use 'else' after 'return'",
          "readability-else-after-return"
        ),
        ClangTidyResult(
          Paths.get("/src/main.c"),
          17,
          1,
          "error",
          "expected identifier or '('",
          "clang-diagnostic-error"
        )
      )

      new ReportParser().parse(output.split("\n").toSeq) should be(expected)
    }
  }

}
