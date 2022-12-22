package shopkart.service

import shopkart.algebra.UserRepository

import shopkart.domain.User

object UserService {

  //todo: move it to a class. 
  def registerUser[F[_]: UserRepository](user: User): F[User] =
    implicitly[UserRepository[F]].save(user)

  def findAll[F[_]: UserRepository]: F[Seq[User]] = implicitly[UserRepository[F]].findAll

}
