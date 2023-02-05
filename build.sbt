import Dependencies._

ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "discogs",
    libraryDependencies += munit % Test,
    libraryDependencies += "com.eed3si9n" %% "gigahorse-apache-http" % "0.7.0",
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
