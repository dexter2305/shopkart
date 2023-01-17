import Dependencies._
// import com.permutive.sbtliquibase.SbtLiquibase

Global / onChangedBuildSource := ReloadOnSourceChanges
ThisBuild / scalaVersion      := "2.13.9"
ThisBuild / version           := "0.1.0"
ThisBuild / organization      := "io.l8"
ThisBuild / organizationName  := "shopkart"
ThisBuild / scalacOptions ++= options.scalacOptions
lazy val commonDependencies = Seq(
  cats_core withJavadoc,
  cats_effect,
  http4s_dsl,
  http4s_circe,
  http4s_blaze_server,
  circe_generic,
  logback_classic,
  pureconfig,
  doobie_core,
  doobie_hikari,
  sqlite
)

lazy val root = (project in file("."))
  .settings(
    name                      := "shopkart",
    libraryDependencies ++= commonDependencies,
    liquibaseChangelog        := java.nio.file.Path.of("src/main/resources/db/changelog.xml").toFile(),
    liquibaseDriver           := "",
    liquibaseUrl              := "jdbc:sqlite:./target/shopkart.db",
    liquibaseUsername         := "",
    liquibasePassword         := "",
    Compile / run / mainClass := Some("shopkart.Main")
  )
  .enablePlugins(SbtLiquibase)

lazy val `a-tf-shopkart` = (project in file("a-tf-shopkart"))
  .settings(
    name                      := "tagless-final-shopkart",
    Compile / run / mainClass := Some("Main"),
    libraryDependencies ++= commonDependencies,
    liquibaseChangelog        := java.nio.file.Path.of("a-tf-shopkart/src/main/resources/db/changelog.xml").toFile(),
    liquibaseDriver           := "org.sqlite.JDBC",
    liquibaseUrl              := "jdbc:sqlite:./target/shopkart.db",
    liquibaseUsername         := "",
    liquibasePassword         := ""
  )
  .enablePlugins(SbtLiquibase)
