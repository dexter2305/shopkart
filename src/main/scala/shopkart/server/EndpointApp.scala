package shopkart.server

import cats.effect._
import org.http4s.server._
import shopkart.endpoints._
import shopkart.algebra._
import shopkart.services._

object EndpointComposer {

  def make[F[_]: Concurrent](userservice: UserService[F]) = ???
    // Router[F] {
    //   // "/" -> UserEndpoints[F](userservice)
    // }.orNotFound
}
