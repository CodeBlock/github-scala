name := "github-scala"

version := "0.0.1"

scalaVersion := "2.9.2"

scalacOptions += "-deprecation"

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype Nexus Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.8" % "test",
  "net.databinder.dispatch" %% "core" % "0.9.0",
  "net.liftweb" %% "lift-json" % "2.5-SNAPSHOT"
)
