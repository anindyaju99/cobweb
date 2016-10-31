import com.typesafe.sbt.SbtStartScript
import de.johoop.findbugs4sbt.FindBugs._
import sbtassembly.Plugin.AssemblyKeys._
import sbtassembly.Plugin._
import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._
import spray.revolver.RevolverPlugin.Revolver

findbugsSettings
jacoco.settings
organization  := "anindyaju99.cobweb"

name          := "cobweb"

scalaVersion  := "2.11.2"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")
com.etsy.sbt.Checkstyle.checkstyleSettings
com.etsy.sbt.Checkstyle.CheckstyleTasks.checkstyleConfig := file("/home/releasebot/jenkins/scripts/gradle/checks.xml")

scalacOptions := Seq(
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-deprecation",
  "-language:postfixOps",
  "-language:implicitConversions",
  "-Xlog-reflective-calls",
  "-Ywarn-adapted-args",
  "-language:existentials",
  "-Ybreak-cycles"   // Fix for cylic issue http://stackoverflow.com/questions/28158173/building-a-project-with-mixed-scala-and-java-source-files-using-ant-illegal-cy
)

/*publishTo := {
  val repository = ""
  if (isSnapshot.value)
    Some("snapshots" at repository + "libs-snapshot-local") 
  else
    Some("releases"  at repository + "libs-release-local")
}*/

//credentials += Credentials(Path.userHome / ".credentials")

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io/",
  "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Clojars repository" at "https://clojars.org/repo"
)

libraryDependencies ++= Seq(
  "io.spray"                        %%   "spray-can"                     % "1.3.2",
  "io.spray"                        %%   "spray-routing"                 % "1.3.2",
  "io.spray"                        %%   "spray-testkit"                 % "1.3.2"                  % "test",
  "io.spray"                        %%   "spray-client"                  % "1.3.2",
  "io.spray"                        %%   "spray-json"                    % "1.3.2",
  "com.github.sstone"               %%   "amqp-client"                   % "1.5",
  "com.typesafe.akka"               %%   "akka-actor"               % "2.4.2",
  "com.typesafe.akka"               %%   "akka-cluster"             % "2.4.2",
  "com.typesafe.akka"               %%   "akka-contrib"             % "2.4.2",
  "com.typesafe.akka"               %%   "akka-slf4j"               % "2.4.2",
  "com.typesafe.akka"               %%   "akka-stream"              % "2.4.2",
  "com.typesafe.akka"               %%   "akka-cluster-tools"       % "2.4.2",
  "org.json4s"                      %%   "json4s-native"                 % "3.3.0",
  "org.json4s"                      %%   "json4s-jackson"                % "3.3.0",
  "ch.qos.logback"                  %    "logback-classic"               % "1.1.2",
  "com.fasterxml.uuid"              %    "java-uuid-generator"           % "3.1.3",
  "com.fasterxml.jackson.core"  % "jackson-databind" % "2.7.3"   ,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.7.2" ,
  "com.codahale.metrics"            %    "metrics-logback"               % "3.0.1",
  "com.codahale.metrics"            %    "metrics-graphite"              % "3.0.1",
  "com.codahale.metrics"            %    "metrics-jvm"                   % "3.0.1",
  "commons-validator"               %    "commons-validator"             % "1.4.0",
  "commons-codec"                   %    "commons-codec"                 % "1.5",
  "com.google.guava"                %    "guava"                         % "15.0",
  "com.notnoop.apns"                %    "apns"                          % "1.0.0.Beta4",
  "org.mongodb"                     %%   "casbah"                        % "2.8.2",
  "commons-io"                      %    "commons-io"                    % "2.4",
  "com.google.code.findbugs"        %    "jsr305"                        % "2.0.3",
  "com.twilio.sdk"                  %    "twilio-java-sdk"               % "3.6.2",
  "org.specs2"                      %%   "specs2"                        % "2.3.11"                 % "test",
  "com.jolbox"                      %    "bonecp"                        % "0.8.0.RELEASE",
  "mysql"                           %    "mysql-connector-java"          % "5.1.34",
  "com.typesafe.slick"              %%   "slick"                         % "2.1.0",
  "com.newrelic.agent.java"         %    "newrelic-api"                  % "3.14.0",
  "org.scalaj"                      %%   "scalaj-http"                   % "1.1.4",
  "us.fatehi"                        % "schemacrawler"                   % "14.03.01",
  "org.scala-lang"                   % "scala-reflect"                   % "2.11.7",
  "org.scala-lang.modules"          %% "scala-xml"                       % "1.0.3",
  "com.github.spullara.mustache.java" %  "compiler"                      % "0.9.0",
  "net.debasishg"                   %%"redisclient"                    % "3.0",
  "io.scalac"                       %% "reactive-rabbit"                 % "1.0.3",
  "com.fasterxml.jackson.core"      % "jackson-core"                     % "2.7.3",
  "junit"                           % "junit"                            % "4.11",
  //"org.apache.httpcomponents"       %%  "httpclient" % "4.5.2"
)

assemblySettings

mergeStrategy in assembly := {
  case x if Assembly.isConfigFile(x) =>
    MergeStrategy.concat
  case PathList(ps @ _*) if Assembly.isReadme(ps.last) || Assembly.isLicenseFile(ps.last) =>
    MergeStrategy.rename
  case x if x.startsWith("META-INF") => MergeStrategy.discard // Bumf
  case x if x.contains("slf4j-api") => MergeStrategy.last
  case PathList("com", "jackson-annotation", xs@_ *) => MergeStrategy.last
  case PathList("org", "jackson-annotation", xs@_ *) => MergeStrategy.last
  case PathList("com", "jackson-core", xs@_ *) => MergeStrategy.last
  case PathList("com", "jackson-databind", xs@_ *) => MergeStrategy.last// For Log$Logger.class
  case PathList("org", "hamcrest", xs@_ *) => MergeStrategy.last
  case _ => MergeStrategy.last

}

parallelExecution in Test := false

assembleArtifact in packageScala := true

test in assembly := {}

jarName in assembly := "cobweb-lib.jar"

logLevel in assembly := Level.Warn

seq(Revolver.settings: _*)

seq(SbtStartScript.startScriptForJarSettings: _*)

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,              // : ReleaseStep
  inquireVersions,                        // : ReleaseStep
  setReleaseVersion,                      // : ReleaseStep
  commitReleaseVersion,                   // : ReleaseStep, performs the initial git checks
  tagRelease,                             // : ReleaseStep
  publishArtifacts,
  setNextVersion,                         // : ReleaseStep
  commitNextVersion,                      // : ReleaseStep
  pushChanges                             // : ReleaseStep, also checks that an upstream branch is properly configured
)


