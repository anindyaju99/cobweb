resolvers += Resolver.url("artifactory", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
 
addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.2") 
 
addSbtPlugin("com.typesafe.sbt" % "sbt-start-script" %  "0.10.0")
 
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")
 
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.7.0")
 
addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.0")
 
addSbtPlugin("de.johoop" % "findbugs4sbt" % "1.4.0")
 
addSbtPlugin("com.etsy" % "sbt-checkstyle-plugin" % "0.5.1")
 
addSbtPlugin("de.johoop" % "jacoco4sbt" % "2.1.6")
