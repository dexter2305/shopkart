package shopkart

import shopkart.server._
import cats.effect.Async

object Program {

  def run[F[_]: Async] = Server.serve(EndpointApp.make[F])

}
