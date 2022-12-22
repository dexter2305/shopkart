package shopkart.endpoint

import cats._
import cats.effect._
import cats.implicits._
import io.circe.generic.auto._
import org.http4s.HttpRoutes
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import shopkart.algebra.UserRepository
import shopkart.domain._
import shopkart.service.UserService

class UserEndpoints[F[_]: UserRepository: Concurrent] private extends Http4sDsl[F] {

  implicit def userEncoder[F[_]]: EntityEncoder[F, User]             = jsonEncoderOf[F, User]
  implicit def userDecoder[F[_]: Concurrent]: EntityDecoder[F, User] = jsonOf[F, User]

  def endpoints: HttpRoutes[F] =
    HttpRoutes.of[F] { case req @ POST -> Root / "userreg" =>
      for {
        requestUser <- req.as[User]
        savedUser   <- UserService.registerUser(requestUser)
        res         <- Ok("done")
      } yield res
    }
}

object UserEndpoints {

  def make[F[_]: UserRepository: Concurrent] = new UserEndpoints[F].endpoints
}
