import sbt._

object Dependencies {
  val scalaTest = "org.scalatest" %% "scalatest" % "3.1.0"
  val codacyCliModel = "com.codacy" %% "codacy-analysis-cli-model" % "7.9.25"
  val codacyEngineScalaSeed = "com.codacy" %% "codacy-engine-scala-seed" % "6.1.4"
  val betterFiles = "com.github.pathikrit" %% "better-files" % "3.9.2"

  val circeVersion = "0.12.3"
  val circe = List("io.circe" %% "circe-core" % circeVersion, "io.circe" %% "circe-generic" % circeVersion)
}
