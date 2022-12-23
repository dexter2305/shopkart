import Dependencies._

Global / onChangedBuildSource := ReloadOnSourceChanges
ThisBuild / scalaVersion      := "2.13.9"
ThisBuild / version           := "0.1.0"
ThisBuild / organization      := "io.l8"
ThisBuild / organizationName  := "shopkart"

lazy val commonDependencies = Seq(
  cats_core withJavadoc,
  cats_effect,
  http4s_dsl,
  http4s_circe,
  http4s_blaze_server,
  circe_generic,
  logback_classic,
)

lazy val root = (project in file("."))
  .settings(
    name := "shopkart"
  )
  .aggregate(`a-tf-shopkart`, `b-rm-shopkart`)

lazy val `a-tf-shopkart` = (project in file("a-tf-shopkart")).settings(
  name                      := "tagless-final-shopkart",
  Compile / run / mainClass := Some("Main"),
  libraryDependencies ++= commonDependencies 
)

lazy val `b-rm-shopkart` = (project in file("b-rm-shopkart")).settings(
  name                      := "reader-monad-shopkart",
  Compile / run / mainClass := Some("Main"),
  libraryDependencies ++= commonDependencies 
)
