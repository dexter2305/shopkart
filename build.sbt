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
  circe_generic
)

lazy val root = (project in file("."))
  .settings(
    name := "shopkart"
  ).aggregate(`shopkart-tf`)

lazy val `shopkart-tf` = (project in file("tf-shopkart")).settings(
  name                      := "shopkart-tf",
  Compile / run / mainClass := Some("Main"),
  libraryDependencies ++= commonDependencies
)
