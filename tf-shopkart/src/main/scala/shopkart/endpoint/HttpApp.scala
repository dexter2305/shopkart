package shopkart.endpoint

import shopkart.algebra.UserRepository
import cats.effect.Concurrent
import org.http4s.server.Router

object HttpApp {

  def make[F[_]: UserRepository: Concurrent] = Router[F] {
    "/" -> UserEndpoints.make
  }.orNotFound
}
