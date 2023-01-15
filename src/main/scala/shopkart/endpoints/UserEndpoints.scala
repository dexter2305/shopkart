package shopkart.endpoints
import cats._
import cats.implicits._
import cats.effect._
import io.circe._
import io.circe.generic.semiauto._
import org.http4s._
import org.http4s.dsl._
import org.http4s.circe._
import shopkart.domain._
import shopkart.codecs._
import shopkart.service.UserService
import shopkart.algebra.UserRepository
class UserEndpoints[F[_]: Concurrent: UserRepository] private extends Http4sDsl[F] {

  def endpoints = HttpRoutes.of[F] {

    case request @ POST -> Root / "users"       =>
      for {
        newUser   <- request.as[User]
        savedUser <- UserService.registerUser(newUser)
        response  <- Ok(savedUser)
      } yield response

    // todo IntVar(userid) should be UserId(x): check rtjvm course example for query parameters
    case GET -> Root / "users" / IntVar(userid) =>
      for {
        maybeUser <- UserService.findById(userid)
        response  <- maybeUser match {
          case None       => NotFound(s"User id $userid not found")
          case Some(user) => Ok(user)
        }
      } yield response
  }
}

object UserEndpoints {

  def make[F[_]: Concurrent: UserRepository] = new UserEndpoints[F].endpoints
}
