import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"
  lazy val pluginsApi = "com.codacy" %% "codacy-plugins-api" % "4.0.1"

  val circeVersion = "0.12.3"

  lazy val circe = List("io.circe" %% "circe-core" % circeVersion,
                        "io.circe" %% "circe-generic" % circeVersion)
}
