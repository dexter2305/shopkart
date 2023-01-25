package shopkart.routes

import cats._, cats.implicits._, cats.effect._
import shopkart.endpoints._, shopkart.domain._
import org.http4s._
import sttp.tapir.server.http4s._

class UserRoutes[F[_]: Async] private {

  private def userFindByID =
    UserEndpoints.userFindById.serverLogicPure[F] { id: Int =>
      User(id, "test", "test").some.asRight[String]
    }

  private def userFindByEmail = UserEndpoints.userFindByEmail.serverLogicPure[F] { email: String =>
    User(-1, "test", email).some.asRight[String]
  }

  private def userCreate = UserEndpoints.userPost.serverLogicPure[F] { user =>
    // fix: assumed save
    user.asRight[String]
  }

  def combined: HttpRoutes[F] =
    (userCreate :: userFindByEmail :: userFindByID :: Nil)
      .map(Http4sServerInterpreter[F]().toRoutes(_))
      .reduce(_ <+> _)

}

object UserRoutes {
  def apply[F[_]: Async] = new UserRoutes[F].combined
}

