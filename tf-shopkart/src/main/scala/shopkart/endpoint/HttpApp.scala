package shopkart.endpoint

import shopkart.algebra._
import cats.effect.Concurrent
import org.http4s.server.Router

import cats._
import cats.implicits._
import org.http4s.server._

object HttpApp {

  def make[F[_]: UserRepository: OrderRepository: Concurrent] = Router[F] {
    "/" -> (UserEndpoints.make[F] <+> OrderEndpoints.make)
  }.orNotFound
}
