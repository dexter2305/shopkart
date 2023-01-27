package shopkart.algebra

import shopkart.domain._
trait UserService[F[_]] {

  def create(user: User): F[Int]

  def findByEmail(email: String): F[Option[User]]

  def findById(id: Int): F[Option[User]]

}
