package shopkart
import cats._
import cats.implicits._
import cats.effect._
import shopkart.algebra.UserRepository
import pureconfig._
import pureconfig.generic.auto._
import shopkart.config.AppConfig

object Main extends IOApp {

  def makeProgram[F[_]: Async: UserRepository] =
    for {
      appConfig <- Resource.eval(
        Async[F].fromEither(
          ConfigSource.default
            .at("shopkart")
            .load[AppConfig]
            .fold(failures => (new Exception(s"$failures")).asLeft[AppConfig], config => config.asRight[Exception])
        )
      )
      server    <- Program.use[F](appConfig)
    } yield server

  override def run(args: List[String]): IO[ExitCode] = {
    implicit val userrepo: UserRepository[IO] = shopkart.interpreter.inmemory.InMemoryUserRepository[IO]

    makeProgram[IO]
      .use(_ =>
        IO {
          println("Press ENTER to terminate")
          scala.io.StdIn.readLine()
        }
      )
      .as(ExitCode.Success)
  }
}
