import sbt._

object Dependencies {

  private object version {

    lazy val cats_core       = "2.9.0"
    lazy val cats_effect     = "3.4.1"
    lazy val http4s          = "0.23.9"
    lazy val circe           = "0.14.3"
    lazy val logback_classic = "1.2.10"
    lazy val sqlite          = "3.34.0"
    lazy val pureconfig      = "0.17.2"
    lazy val doobie          = "1.0.0-RC1"

  }

  lazy val cats_core           = "org.typelevel"         %% "cats-core"            % version.cats_core
  lazy val cats_effect         = "org.typelevel"         %% "cats-effect"          % version.cats_effect
  lazy val http4s_dsl          = "org.http4s"            %% s"http4s-dsl"          % version.http4s
  lazy val http4s_ember_server = "org.http4s"            %% s"http4s-ember-server" % version.http4s
  lazy val http4s_blaze_server = "org.http4s"            %% s"http4s-blaze-server" % version.http4s
  lazy val http4s_circe        = "org.http4s"            %% "http4s-circe"         % version.http4s
  lazy val circe_core          = "io.circe"              %% "circe-core"           % version.circe
  lazy val circe_generic       = "io.circe"              %% "circe-generic"        % version.circe
  lazy val circe_literal       = "io.circe"              %% "circe-literal"        % version.circe
  lazy val logback_classic     = "ch.qos.logback"         % "logback-classic"      % version.logback_classic
  lazy val sqlite              = "org.xerial"             % "sqlite-jdbc"          % version.sqlite
  lazy val pureconfig          = "com.github.pureconfig" %% "pureconfig"           % version.pureconfig
  lazy val doobie_core         = "org.tpolecat"          %% "doobie-core"          % version.doobie
  lazy val doobie_hikari       = "org.tpolecat"          %% "doobie-hikari"        % version.doobie
  lazy val doobie_pg           = "org.tpolecat"          %% "doobie-postgres"      % version.doobie

}

object options {
  val scalacOptions = Seq(
    "-deprecation"
  )
}

object liquibase {}
