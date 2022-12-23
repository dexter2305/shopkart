package shopkart.services

import shopkart.domain._
import cats._
import cats.data._

object UserService {

  def save(user: User): Reader[UserRepository, User] =
    for {
      userRepository <- Reader(identity[UserRepository])
      savedUser = userRepository.save(user)
    } yield savedUser
}
