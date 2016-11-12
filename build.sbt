import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

name := "sentiments-service"

organization := "com.kirill"

version := "0.1.0"

scalaVersion := "2.11.8"

crossScalaVersions := Seq("2.11.8", "2.12.0")

resolvers ++= Seq(
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
)

libraryDependencies ++= Seq(

  //"com.chuusai" %% "shapeless" % "2.3.2",
  //"org.typelevel" %% "cats" % "0.8.0",

  //"com.typesafe.akka" %% "akka-actor" % "2.4.12",
  //"com.typesafe.akka" %% "akka-slf4j" % "2.4.12",
  //"com.typesafe.akka" %% "akka-stream" % "2.4.12",
  //"com.typesafe.akka" %% "akka-stream-testkit" % "2.4.12",
  //"com.typesafe.akka" %% "akka-testkit" % "2.4.12" % "test",

  //"com.typesafe.akka" %% "akka-http-core" % "2.4.11",
  //"com.typesafe.akka" %% "akka-http-experimental" % "2.4.11",
  //"com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.4.11",
  //"com.typesafe.akka" %% "akka-http-testkit" % "2.4.11",

  "org.scalatest" %% "scalatest" % "3.0.0" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test"
)

libraryDependencies += "org.deeplearning4j" % "deeplearning4j-core" % "0.6.0"
libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.2.7"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.0.1"

libraryDependencies += "org.apache.spark" % "spark-mllib_2.11" % "2.0.1"

libraryDependencies += "com.typesafe" % "config" % "1.3.1"

libraryDependencies += "oncue.journal" %% "core" % "2.2.1"

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "io.spray"            %%  "spray-json"    % "1.3.2",
    "io.spray"            %%  "spray-testkit" % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test",
    "org.specs2"          %%  "specs2-core"   % "2.3.11" % "test"
  )
}

scalacOptions ++= Seq(
    "-target:jvm-1.8",
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-language:experimental.macros",
    "-unchecked",
    //"-Ywarn-unused-import",
    "-Ywarn-nullary-unit",
    "-Xfatal-warnings",
    "-Xlint",
    //"-Yinline-warnings",
    "-Ywarn-dead-code",
    "-Xfuture")


initialCommands := "import ml._"

SbtScalariform.scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(RewriteArrowSymbols, true)

Revolver.settings