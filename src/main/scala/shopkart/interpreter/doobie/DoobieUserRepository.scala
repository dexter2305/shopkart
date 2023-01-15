package shopkart.interpreter.doobie

import shopkart.algebra._
import shopkart.domain.User

trait DoobieUserRepository[F[_]] extends UserRepository[F] {

  override def save(user: User): F[User] = ???

  override def findById(id: Int): F[Option[User]] = ???

}
