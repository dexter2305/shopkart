package shopkart.algebra

import shopkart.domain._

trait UserRepository[F[_]] {

  def save(user: User):F[User]
  def findById(id: Int): F[Option[User]]
  def findByEmail(email: String): F[Option[User]]

}