package shopkart.server

import org.http4s.server.Router
import shopkart.endpoints.UserEndpoints
import cats._
import org.http4s.server._
import org.http4s.HttpApp

object EndpointApp {

  def make[F[_]: Monad]: HttpApp[F] = Router {
    "/" -> UserEndpoints.make[F]
  }.orNotFound
}
