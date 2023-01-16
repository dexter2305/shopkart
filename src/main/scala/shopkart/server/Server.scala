package shopkart.server

import org.http4s.server._
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s
import cats._, cats.effect._
import shopkart.interpreter.inmemory._
import shopkart.config._
import shopkart.domain._
import shopkart.services._
object Server {

  def make[F[_]: Monad: Async](config: AppConfig) = {
    val repo = InmemoryUserRepository[F](List(User(1, "dexter", "dexter@g.c"), User(2, "light", "light@dn.io")))
    for {
      server <- BlazeServerBuilder[F]
        .bindHttp(config.http.port, config.http.host)
        .withHttpApp(EndpointComposer.make[F](UserService.make[F](repo)))
        .resource
    } yield server
  }
}
