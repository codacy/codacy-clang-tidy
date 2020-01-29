# codacy-clang-tidy

A standalone tool that converts [Clang-Tidy](https://clang.llvm.org/extra/clang-tidy/)
reports to Codacy's format. It allows the integration of Clang-Tidy into your Codacy workflow.

## Usage

TODO

## Building

##### Compile

`sbt compile`

##### Formatting

`sbt ";scalafmt;test:scalafmt;sbt:scalafmt"`

##### Tests

`sbt test`

##### Building native image (requires docker)

`sbt "graalvm-native-image:packageBin"`
