package shopkart.server

import org.http4s.server._
import shopkart.endpoints._
import cats.effect._
import org.http4s.server._
import org.http4s._
import shopkart.algebra._

object EndpointApp {

  def make[F[_]: Concurrent: UserRepository]: HttpApp[F] = Router {
    "/" -> UserEndpoints.make[F]
  }.orNotFound
}
