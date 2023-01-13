package shopkart.endpoints
import cats._
import cats.implicits._
import org.http4s._
import org.http4s.dsl._

class UserEndpoints[F[_]: Monad] private extends Http4sDsl[F] {

  def endpoints = HttpRoutes.of[F] {

    case request @ POST -> Root / "users" => ???

    case GET -> Root / "users" / IntVar(userid) => ???

  }
}

object UserEndpoints {

  def make[F[_]: Monad] = new UserEndpoints[F].endpoints
}
