resolvers += Resolver.typesafeRepo("releases")

addSbtPlugin("com.codacy" % "codacy-sbt-plugin" % "18.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.6.0")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.1")

addSbtPlugin("com.codacy" % "sbt-codacy-coverage" % "3.0.3")