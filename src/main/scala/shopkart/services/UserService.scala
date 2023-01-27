package shopkart.services

import shopkart.algebra._
import shopkart.domain._
import shopkart.algebra

class UserService[F[_]] private (userrepo: UserRepository[F]) extends algebra.UserService[F] {

  override def create(user: User)         = userrepo.insert(user)
  override def findByEmail(email: String) = userrepo.findByEmail(email)
  override def findById(id: Int)          = userrepo.findById(id)

}

object UserService {
  def make[F[_]](userrepo: UserRepository[F]) = new UserService[F](userrepo)
}
