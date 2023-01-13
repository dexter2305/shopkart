package shopkart.service

import shopkart.algebra.UserRepository
import shopkart.domain._

object UserService {

  def registerUser[F[_]: UserRepository](newUser: User) = implicitly[UserRepository[F]].save(newUser)

  def findById[F[_]: UserRepository](id: Int) = implicitly[UserRepository[F]].findById(id)
}