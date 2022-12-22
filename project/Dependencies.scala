import sbt._

object Dependencies {

  private object version {
    lazy val cats_core   = "2.9.0"
    lazy val cats_effect = "3.4.1"
    lazy val http4s      = "0.23.9"
    lazy val circe       = "0.14.3"
  }

  lazy val cats_core           = "org.typelevel" %% "cats-core"            % version.cats_core
  lazy val cats_effect         = "org.typelevel" %% "cats-effect"          % version.cats_effect
  lazy val http4s_dsl          = "org.http4s"    %% s"http4s-dsl"          % version.http4s
  lazy val http4s_ember_server = "org.http4s"    %% s"http4s-ember-server" % version.http4s
  lazy val http4s_blaze_server = "org.http4s"    %% s"http4s-blaze-server" % version.http4s
  lazy val http4s_circe        = "org.http4s"    %% "http4s-circe"         % version.http4s
  lazy val circe_core          = "io.circe"      %% "circe-core"           % version.circe
  lazy val circe_generic       = "io.circe"      %% "circe-generic"        % version.circe
  lazy val circe_literal       = "io.circe"      %% "circe-literal"        % version.circe
}
