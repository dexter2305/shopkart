import cats.effect.IOApp
import cats.effect.{ExitCode, IO}
import shopkart.program._
import shopkart.algebra.UserRepository
import shopkart.algebra.OrderRepository
object Main extends IOApp {

  def run(args: List[String]): IO[ExitCode] = {


    import shopkart.interpreter.inmemory._
    implicit val userRepository: UserRepository[IO]   = InmemoryUserRepository.make
    implicit val orderRepository: OrderRepository[IO] = InmemoryOrderRepository.make
    Program.dsl[IO].as(ExitCode.Success)


  }
}
