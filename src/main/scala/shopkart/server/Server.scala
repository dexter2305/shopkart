package shopkart.server

import doobie._, doobie.implicits._, doobie.hikari._
import org.http4s.server._
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s
import cats._, cats.effect._, cats.implicits._
import shopkart.config._
import shopkart.domain._
import shopkart.services._
import shopkart.interpreter.doobie._
object Server {

  def make[F[_]: Monad: Async](config: AppConfig) =
    for {
      ec <- ExecutionContexts.cachedThreadPool[F]
      xa <- HikariTransactor
        .newHikariTransactor(config.db.driver, config.db.url, config.db.user, config.db.password, ec)
      userrepo = DoobieUserRepository[F](xa)
      server <-
        BlazeServerBuilder[F]
          .bindHttp(config.http.port, config.http.host)
          .withHttpApp(EndpointComposer.make[F](UserService.make[F](userrepo)))
          .resource
    } yield server
}
