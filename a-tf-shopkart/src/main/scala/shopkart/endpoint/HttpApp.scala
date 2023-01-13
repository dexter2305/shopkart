package shopkart.endpoint

import shopkart.algebra._
import cats.effect.Concurrent
import org.http4s.server.Router

import cats._
import cats.implicits._
import org.http4s.server._
import org.http4s

object HttpApp {

  def make[F[_]: UserRepository: OrderRepository: Concurrent]: http4s.HttpApp[F] = Router[F] {
    "/" -> (UserEndpoints.make[F] <+> OrderEndpoints.make)
  }.orNotFound
}
