package shopkart.interpreter.inmemory

import shopkart.algebra.UserRepository
import shopkart.domain.User
import scala.collection.concurrent.TrieMap
import cats._
import cats.implicits._
class InMemoryUserRepository[F[_]: Applicative] private extends UserRepository[F] {
  var repo                                        = TrieMap.empty[Int, User]
  override def save(user: User): F[User]          = {
    repo.put(user.id, user)
    user.pure[F]
  }
  override def findById(id: Int): F[Option[User]] = repo.find(_._1 === id).map(t => t._2).pure[F]
}

object InMemoryUserRepository {

  def apply[F[_]: Applicative] = new InMemoryUserRepository[F]
}
