package shopkart.algebra

import shopkart.domain._

trait UserRepository[F[_]] {

  def insert(user: User): F[Int]

  def update(user: User): F[Unit]

  def findById(id: Int): F[Option[User]]

  def findByEmail(email: String): F[Option[User]]

}
