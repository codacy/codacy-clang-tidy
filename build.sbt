import Dependencies._

ThisBuild / scalaVersion := "2.13.1"
ThisBuild / organization := "com.codacy"
ThisBuild / organizationName := "codacy"

val graalVersion = "19.3.1-java11"

lazy val root = (project in file("."))
  .enablePlugins(GraalVMNativeImagePlugin)
  .settings(
    name := "codacy-clang-tidy",
    libraryDependencies ++= Seq(pluginsApi) ++ circe,
    libraryDependencies += scalaTest % Test,
    scalacOptions += "-Ywarn-macros:after", // checks for unused implicits after macro expansion
    addCompilerPlugin("io.tryp" % "splain" % "0.5.0" cross CrossVersion.patch),
    test in assembly := {},
    // Graal vm build options
    graalVMNativeImageGraalVersion := Some(graalVersion),
    containerBuildImage := Some(s"oracle/graalvm-ce:$graalVersion"),
    graalVMNativeImageOptions ++= Seq(
      "-O1",
      "-H:+ReportExceptionStackTraces",
      "--no-fallback",
      "--no-server",
      "--initialize-at-build-time",
      "--report-unsupported-elements-at-runtime",
      "--static"
    )
  )
