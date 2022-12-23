package shopkart.endpoints
import cats.data._
import cats.effect._
import cats.implicits._
import shopkart.domain._
import org.http4s._
import org.http4s.server._

object Routes {

  def make: Reader[UserRepository, HttpApp[IO]] =
    for {
      userEndpoints <- UserEndpoints.make[IO]
      app = Router[IO] {
        "/" -> userEndpoints
      }.orNotFound
    } yield app
}
