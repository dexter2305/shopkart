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
import shopkart.services._
import shopkart.algebra._
class UserEndpoints[F[_]: Concurrent] private (userservice: UserService[F]) extends Http4sDsl[F] {

  case class Email(value: String)
  object EmailVar {
    def unapply(str: String): Option[Email] = Option(Email(str))
  }

  def endpoints: HttpRoutes[F] = HttpRoutes.of[F] {

    case req @ POST -> Root / "users" =>
      for {
        userInRequest <- req.as[User]
        savedUser = userservice.save(userInRequest)
        response <- Ok(savedUser)
      } yield response

    case GET -> Root / "users" / EmailVar(emailid) =>
      for {
        maybeUser <- userservice.findByEmail(emailid.value)
        response  <- maybeUser match {
          case None       => NotFound(s"Email id $emailid not found")
          case Some(user) => Ok(user)
        }
      } yield response
  }
}
object UserEndpoints {
  def apply[F[_]: Concurrent](userservice: UserService[F]) = new UserEndpoints[F](userservice).endpoints
}
