package shopkart

import cats._
import cats.implicits._
import cats.effect._
import shopkart.algebra.UserRepository
import pureconfig._
import pureconfig.generic.auto._
import shopkart.config.AppConfig
import shopkart.server.Server

object Main extends IOApp {

  override def run(args: List[String]): IO[ExitCode] = program[IO]
    .use(server =>
      IO {
        println("Enter to terminate")
        scala.io.StdIn.readLine()
      }
    )
    .as(ExitCode.Success)

  def program[F[_]: Async] =
    for {
      config <- Resource.eval(
        Async[F].fromEither(
          ConfigSource.default
            .at("shopkart")
            .load[AppConfig]
            .fold(e => (new Exception()).asLeft[AppConfig], c => c.asRight[Exception])
        )
      )
      server <- Server.make[F](config)
    } yield server

}
