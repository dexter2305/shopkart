package shopkart.program

import shopkart.server._
import shopkart.endpoint._
import shopkart.algebra.UserRepository
import cats.effect.{Async, Concurrent}
import shopkart.algebra.OrderRepository
import cats.effect.ExitCode
object Program {

  def dsl[F[_]: UserRepository: OrderRepository: Concurrent: Async]: F[Unit] = Server.serve[F](HttpApp.make[F])

}
