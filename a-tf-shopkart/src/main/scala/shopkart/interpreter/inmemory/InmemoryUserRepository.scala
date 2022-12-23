package shopkart.interpreter.inmemory
import shopkart.algebra.UserRepository
import shopkart.domain.User
import scala.collection.concurrent.TrieMap
import cats._
import cats.implicits._
import cats.effect._

class InmemoryUserRepository[F[_]: Sync] private extends UserRepository[F] {

  private val store: TrieMap[Int, User] = TrieMap.empty[Int, User]

  override def save(user: User): F[User] =
    Sync[F].defer {
      store.put(user.id, user)
      user.pure[F]
    }

  override def findById(id: Int): F[Option[User]] =
    Sync[F].defer(store.get(id).pure[F])

  override def findByEmail(email: String): F[Option[User]] =
    Sync[F].defer(store.values.find(_.email === email).pure[F])

  override def findAll: F[Seq[User]] =
    Sync[F].defer(store.values.toSeq.pure[F])

}

object InmemoryUserRepository {

  def make[F[_]: Sync] = new InmemoryUserRepository[F]
}
