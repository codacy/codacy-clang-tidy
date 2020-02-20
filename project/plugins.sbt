resolvers += Resolver.typesafeRepo("releases")
resolvers += Resolver.JCenterRepository

addSbtPlugin("com.codacy" % "codacy-sbt-plugin" % "18.0.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.6.0")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.10")

addSbtPlugin("org.scoverage" % "sbt-scoverage" % "1.6.1")
