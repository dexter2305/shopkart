package shopkart.impls
import shopkart.domain.User
import shopkart.domain.UserRepository
import scala.collection.concurrent.TrieMap

class UserRepositoryInMemory private extends UserRepository {

  var store: TrieMap[Int, User] = TrieMap.empty

  override def save(user: User): User = {
    store = store + (user.id -> user)
    user
  }

  override def findById(id: Int): Option[User] = store.get(id)

  override def findByEmail(email: String): Option[User] = store.values.find(_.email == email)

}

object UserRepositoryInMemory {

  def make = new UserRepositoryInMemory
}
