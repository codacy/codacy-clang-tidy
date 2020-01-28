import Dependencies._

ThisBuild / scalaVersion := "2.13.1"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.codacy"
ThisBuild / organizationName := "codacy"

lazy val root = (project in file("."))
  .settings(
    name := "codacy-clang-tidy",
    libraryDependencies ++= Seq(pluginsApi) ++ circe,
    libraryDependencies += scalaTest % Test,
    scalacOptions += "-Ywarn-macros:after", // checks for unused implicits after macro expansion
    addCompilerPlugin("io.tryp" % "splain" % "0.5.0" cross CrossVersion.patch)
  )
