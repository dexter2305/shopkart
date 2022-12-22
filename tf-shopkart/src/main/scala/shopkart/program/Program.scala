package shopkart.program

import shopkart.server._
import shopkart.endpoint._
import shopkart.algebra.UserRepository
import cats.effect.{Async, Concurrent}
object Program {

  def dsl[F[_]: UserRepository: Concurrent: Async]: F[Unit] = Server.serve[F](HttpApp.make[F])

}
