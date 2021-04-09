import Dependencies._

ThisBuild / scalaVersion := "2.13.1"
ThisBuild / organization := "com.codacy"
ThisBuild / organizationName := "codacy"

lazy val root = (project in file("."))
  .enablePlugins(GraalVMNativeImagePlugin)
  .settings(
    name := "codacy-clang-tidy",
    libraryDependencies ++= Seq(codacyCliModel, "com.github.scopt" %% "scopt" % "3.7.1", scalaTest % Test) ++ circe,
    scalacOptions += "-Ywarn-macros:after", // checks for unused implicits after macro expansion
    addCompilerPlugin("io.tryp" % "splain" % "0.5.0" cross CrossVersion.patch),
    test in assembly := {},
    // Graal vm build options
    graalVMNativeImageGraalVersion := Some("21.0.0"),
    graalVMNativeImageOptions ++= Seq(
      "-O1",
      "-H:+ReportExceptionStackTraces",
      "--no-fallback",
      "--no-server",
      "--report-unsupported-elements-at-runtime",
      "--static"
    )
  )

lazy val `doc-generator` = project
  .settings(libraryDependencies ++= Seq(codacyEngineScalaSeed, betterFiles))
