package shopkart.server

import cats.data._
import cats.effect._
import org.http4s.blaze.server.BlazeServerBuilder
import shopkart.domain._
import org.http4s.HttpApp
import com.comcast.ip4s.Literals

object Server {

  def serve(app: HttpApp[IO]): IO[Unit] =
    BlazeServerBuilder[IO]
      .bindHttp(port = 8080, host = "0.0.0.0")
      .withHttpApp(app)
      .serve
      .compile
      .drain

}
