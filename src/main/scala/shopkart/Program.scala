package shopkart

import shopkart.config.AppConfig
import cats.effect._
import org.http4s.server._
import shopkart.server._
import shopkart.algebra.UserRepository
object Program {

  def use[F[_]: Async: UserRepository](config: AppConfig): Resource[F, Server] =
    Server.make(config.http.host, config.http.port, EndpointApp.make)
}
