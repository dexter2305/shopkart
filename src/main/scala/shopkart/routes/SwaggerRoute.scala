package shopkart.routes

import shopkart.endpoints._
import cats.effect._
import sttp.tapir.swagger.bundle.SwaggerInterpreter
import sttp.tapir.server.http4s.Http4sServerInterpreter
class SwaggerRoute[F[_]: Async]private  {

  def combined = Http4sServerInterpreter[F]().toRoutes(
    SwaggerInterpreter().fromEndpoints[F](UserEndpoints.combined, "Shopping Kart API", "1.0")
  )
}

object SwaggerRoute {

  def apply[F[_]: Async] = new SwaggerRoute[F].combined
}