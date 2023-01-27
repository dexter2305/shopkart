package shopkart.routes

import cats._, cats.implicits._, cats.effect._
import shopkart.endpoints._, shopkart.domain._, shopkart.services._
import org.http4s._
import sttp.tapir.server.http4s._, sttp.model._

class UserRoutes[F[_]: Async] private (service: UserService[F]) {

  private def userFindByID =
    UserEndpoints.userFindById.serverLogic[F] { id: Int =>
      for {
        mayBeUser <- service.findById(id)
        userOrErrr = mayBeUser match {
          case None       => StatusCode.NotFound.asLeft[User]
          case Some(user) => user.asRight[StatusCode]
        }
      } yield userOrErrr
    }

  private def userFindByEmail = UserEndpoints.userFindByEmail.serverLogic[F] { email: String =>
    for {
      mayBeUser <- service.findByEmail(email)
      userOrError = mayBeUser match {
        case None       => StatusCode.NotFound.asLeft[User]
        case Some(user) => user.asRight[StatusCode]
      }
    } yield userOrError

  }

  private def userCreate = UserEndpoints.userPost.serverLogic[F] { user =>
    for {
      id <- service.create(user)
    } yield id.asRight[StatusCode]
  }

  def combined: HttpRoutes[F] =
    (userCreate :: userFindByEmail :: userFindByID :: Nil)
      .map(Http4sServerInterpreter[F]().toRoutes(_))
      .reduce(_ <+> _)
}

object UserRoutes {
  def apply[F[_]: Async](service: UserService[F]) = new UserRoutes[F](service).combined
}
