package shopkart.services

import shopkart.algebra._
import shopkart.domain._

class UserService[F[_]] private (userrepo: UserRepository[F]) {

  def save(user: User)           = userrepo.save(user)
  def findByEmail(email: String) = userrepo.findByEmail(email)

}

object UserService {
  def make[F[_]](userrepo: UserRepository[F]) = new UserService[F](userrepo)
}
