package shopkart.endpoints

import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes
import cats._
import cats.data._
import cats.implicits._
import cats.effect._
import shopkart.domain._
import shopkart.codecs._
import shopkart.services.UserService

class UserEndpoints[F[_]: Concurrent] private extends Http4sDsl[F] {

  def endpoints: Reader[UserRepository, HttpRoutes[F]] =
    for {
      userRepo <- Reader(identity[UserRepository])
      routes = HttpRoutes.of[F] {
        case req @ POST -> Root / "users" =>
          for {
            reqUser <- req.as[User]
            savedUser = userRepo.save(reqUser)
            response <- Ok(savedUser)
          } yield response

        case GET -> Root / "users" / IntVar(userid) =>
          userRepo.findById(userid) match {
            case None       => NotFound(s"User id $userid not found")
            case Some(user) => Ok(user)
          }

        case GET -> Root / "users" / emailid =>
          userRepo.findByEmail(email = emailid) match {
            case None       => NotFound(s"Email id $emailid not found")
            case Some(user) => Ok(user)
          }
      }
    } yield routes
}

object UserEndpoints {

  def make[F[_]: Concurrent] = new UserEndpoints[F].endpoints
}
