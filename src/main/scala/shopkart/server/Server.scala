package shopkart.server
import cats.effect.Async
import org.http4s.server._
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s
object Server {

  //todo return the server as resource to make it race with a cancellable task
  def serve[F[_]: Async](app: http4s.HttpApp[F]) =
    BlazeServerBuilder[F]
      .bindHttp(port = 8080, host = "0.0.0.0")
      .withHttpApp(app)
      .serve
      .compile
      .drain
}
