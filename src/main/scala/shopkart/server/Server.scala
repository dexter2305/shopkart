package shopkart.server
import cats.effect.Async
import org.http4s.server._
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s
import cats.effect.kernel.Resource
object Server {

  // todo return the server as resource to make it race with a cancellable task
  def make[F[_]: Async](host: String, port: Int, app: http4s.HttpApp[F]): Resource[F, http4s.server.Server] =
    BlazeServerBuilder[F]
      .bindHttp(port = port, host = host)
      .withHttpApp(app)
      .resource
}
