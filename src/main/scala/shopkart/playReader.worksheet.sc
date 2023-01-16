import shopkart.service
import cats._
import io.circe._
import io.circe.generic._
import cats.data._
import cats.implicits._
import cats.effect._
import org.http4s._
import org.http4s.dsl._

case class User(name: String, email: String)
object User {
  implicit def codec:Codec[User] = ???
  implicit def userEntityEncoder[F[_]]: EntityEncoder[F, User] = ???
  implicit def userEntityDecoder[F[_]]: EntityDecoder[F, User] = ???
}
trait UserRepository {
  def save(user: User): User
  def find(email: String): Option[User]
}

trait UserService {
  def save(user: User): Reader[UserRepository, User]
  def findByEmail(email: String): Reader[UserRepository, User]
}

class UserEndpoints[F[_]: Monad] extends Http4sDsl[F] {

}
