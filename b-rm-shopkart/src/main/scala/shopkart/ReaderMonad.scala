package shopkart

package object domain {

  case class User(id: Int, email: String)
  trait UserRepository {
    def save(user: User): User
    def findById(id: Int): Option[User]
    def findByEmail(email: String): Option[User]
  }
}

package object services {
  import cats.data._
  import domain._
  class UserService {

    val userRepoReader: Reader[UserRepository, UserRepository] = Reader(identity[UserRepository])

    def save(user: User): Reader[UserRepository, User] = userRepoReader.map(repo => repo.save(user))

    def findByEmail(email: String): Reader[UserRepository, Option[User]] = userRepoReader.map(repo => repo.findByEmail(email))

    def findById(id: Int): Reader[UserRepository, Option[User]] = userRepoReader.map(_.findById(id))

  }
}



object ReaderMonadApp {}
