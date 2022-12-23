package shopkart.domain

trait UserRepository {

  def save(user: User): User
  def findById(id: Int): Option[User]
  def findByEmail(email: String): Option[User]
}
