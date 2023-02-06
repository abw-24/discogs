import Dependencies._

ThisBuild / scalaVersion     := "2.13.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "discogs",
    libraryDependencies += munit % Test,
    libraryDependencies += "com.eed3si9n" %% "gigahorse-apache-http" % "0.7.0",
    libraryDependencies += "com.typesafe.play" %% "play-json" % "2.9.4"
)

