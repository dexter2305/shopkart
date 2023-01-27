package shopkart.interpreter.doobie

import cats.implicits._, cats.effect._
import doobie._, doobie.implicits._
import shopkart.algebra._
import shopkart.domain.User

class DoobieUserRepository[F[_]: MonadCancelThrow] private (xa: Transactor[F]) extends UserRepository[F] {

  implicit val logger = LogHandler.jdkLogHandler

  override def update(user: User): F[Unit] = ???

  override def insert(user: User): F[Int] =
    for {
      id <- sql"insert into users (id, name, email) values (${user.id}, ${user.name}, ${user.email})".update.run
        .transact(xa)
    } yield id

  override def findById(id: Int): F[Option[User]] =
    for {
      user <- sql"select id, name, email from users where id = $id".query[User].option.transact(xa)
    } yield user

  override def findByEmail(email: String): F[Option[User]] = 
    for {
      user <- sql"select id, name, email from users where email = $email".query[User].option.transact(xa)
    } yield user

}

object DoobieUserRepository {

  def apply[F[_]: MonadCancelThrow](xa: Transactor[F]) = new DoobieUserRepository[F](xa)
}
