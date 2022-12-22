package shopkart.service

import shopkart.algebra.UserRepository

import shopkart.domain.User

object UserService {

  def registerUser[F[_]: UserRepository](user: User): F[User] =
    implicitly[UserRepository[F]].save(user)

}
