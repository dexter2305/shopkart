package shopkart.interpreter.inmemory

import shopkart.algebra._
import shopkart.domain._

import cats._, cats.implicits._, cats.effect._
class InmemoryUserRepository[F[_]: Sync] private (initial: Seq[User]) extends UserRepository[F] {

  var store: Seq[User] = Seq.from(initial)

  override def findById(id: Int): F[Option[User]] = Sync[F].defer {
    store.find(_.id === id).pure[F]
  }

  override def save(user: User): F[User]                   = Sync[F].defer {
    store = store :+ user
    user.pure[F]
  }
  override def findByEmail(email: String): F[Option[User]] = Sync[F].defer {
    store.find(_.email === email).pure[F]
  }
}
object InmemoryUserRepository {

  def apply[F[_]: Sync](users: Seq[User]) = new InmemoryUserRepository[F](users)
}
