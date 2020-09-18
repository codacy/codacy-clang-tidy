import sbt._

object Dependencies {
  val scalaTest = "org.scalatest" %% "scalatest" % "3.1.0"
  val codacyCliModel = "com.codacy" %% "codacy-analysis-cli-model" % "2.2.0"
  val codacyEngineScalaSeed = "com.codacy" %% "codacy-engine-scala-seed" % "5.0.1"
  val betterFiles = "com.github.pathikrit" %% "better-files" % "3.8.0"

  val circeVersion = "0.12.3"
  val circe = List("io.circe" %% "circe-core" % circeVersion, "io.circe" %% "circe-generic" % circeVersion)
}
