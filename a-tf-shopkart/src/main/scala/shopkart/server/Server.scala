package shopkart.server

import org.http4s.HttpApp
import org.http4s.blaze.server.BlazeServerBuilder
import cats.effect._
import cats.implicits._
import com.comcast.ip4s._
object Server {

  def serve[F[_]: Async](httpApp: HttpApp[F]): F[Unit] =
    BlazeServerBuilder[F]
      .bindHttp(port = 9090, host = "0.0.0.0")
      .withHttpApp(httpApp)
      .serve
      .compile
      .drain


}
