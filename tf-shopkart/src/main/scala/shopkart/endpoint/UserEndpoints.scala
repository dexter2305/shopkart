package shopkart.endpoint

import cats.effect._
import cats.implicits._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl._
import shopkart.algebra._
import shopkart.domain._
import shopkart.service._
class UserEndpoints[F[_]: UserRepository: OrderRepository: Concurrent] private
    extends Http4sDsl[F] {

  implicit def userEncoder[F[_]]: EntityEncoder[F, User]             = jsonEncoderOf[F, User]
  implicit def usersEncoder[F[_]]: EntityEncoder[F, Seq[User]]       = jsonEncoderOf[F, Seq[User]]
  implicit def userDecoder[F[_]: Concurrent]: EntityDecoder[F, User] = jsonOf[F, User]
  implicit def orderEncoder[F[_]]: EntityEncoder[F, Seq[Order]]      = jsonEncoderOf[F, Seq[Order]]

  def endpoints: HttpRoutes[F] = HttpRoutes.of[F] {

    case req @ POST -> Root / "users" =>
      for {
        requestUser <- req.as[User]
        savedUser   <- UserService.registerUser(requestUser)
        res         <- Ok("done")
      } yield res

    case GET -> Root / "users" =>
      for {
        users    <- UserService.findAll
        response <- Ok(users)
      } yield response
      ???

    case GET -> Root / "users" / IntVar(userid) / "orders" =>
      for {
        orders   <- OrderService[F].findOrdersByUser(userid)
        response <- Ok(orders)
      } yield response

  }
}

object UserEndpoints {

  def make[F[_]: UserRepository: OrderRepository: Concurrent] = new UserEndpoints[F].endpoints
}
