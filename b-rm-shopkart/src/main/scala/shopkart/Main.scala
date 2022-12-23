
import cats.data._
import cats.effect.IOApp
import cats.effect.{ExitCode, IO}
import shopkart.impls._
import shopkart.domain._
import shopkart.endpoints._
import shopkart.server._
object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {

    val repo                                          = UserRepositoryInMemory
    val program: Reader[UserRepository, IO[ExitCode]] = for {
      app <- Routes.make
      rc = Server.serve(app)
    } yield rc.as(ExitCode.Success)

    //program.run(repo)
    ???
  }
}
