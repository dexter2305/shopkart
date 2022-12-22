package shopkart.interpreter.inmemory
import shopkart.algebra.UserRepository
import shopkart.domain.User
import scala.collection.concurrent.TrieMap
import cats._
import cats.implicits._

class InmemoryUserRepository[F[_]: Applicative] private extends UserRepository[F] {

  private val store: TrieMap[Int, User] = TrieMap.empty[Int, User]

  override def save(user: User): F[User] = {
    store.put(user.id, user)
    user.pure[F]
  }

  override def findById(id: Int): F[Option[User]] =
    store.get(id).pure[F]

  override def findByEmail(email: String): F[Option[User]] =
    store.values.find(_.email === email).pure[F]
}

object InmemoryUserRepository {

  def make[F[_]: Applicative] = new InmemoryUserRepository[F]
}
