package shopkart.interpreter.quill

import cats._, cats.implicits._, cats.effect._
import io.getquill._
import shopkart._

class UserRepository[F[_]: Sync] extends algebra.UserRepository[F] {

  private lazy val ctx = new SqliteJdbcContext(LowerCase, "ctx")
  import ctx._

  private val users = quote {
    querySchema[domain.User]("Users")
  }

  override def insert(user: domain.User): F[Int] =
    Sync[F].delay(
      ctx.run(
        users.insert(lift(user)).returningGenerated(_.id)
      )
    )

  override def update(user: domain.User): F[Unit] = ???

  override def findById(id: Int): F[Option[domain.User]] = ???

  override def findByEmail(email: String): F[Option[domain.User]] = {
    Sync[F].delay {
      val x = ctx.run(
        users
          .filter(_.email == lift(email))
      )
    }
    ???
  }

}
