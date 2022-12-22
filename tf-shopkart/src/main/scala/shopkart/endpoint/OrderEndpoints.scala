package shopkart.endpoint

import org.http4s.dsl.Http4sDsl
import org.http4s.HttpRoutes
import shopkart._
import shopkart.algebra._
import shopkart.service._
import cats.effect._
import cats._
import cats.implicits._
import org.http4s.circe._

import org.http4s.EntityDecoder
import cats.instances.order

class OrderEndpoints[F[_]: OrderRepository: Concurrent] private extends Http4sDsl[F] {

  implicit val encoder            = jsonEncoderOf[F, domain.Order]
  implicit val orderEntityDecoder = jsonOf[F, domain.Order]

  def endpoints: HttpRoutes[F] = HttpRoutes.of[F] {

    case GET -> Root / "orders" / IntVar(orderid) =>
      OrderService.apply.findOrderById(orderid).flatMap {
        case None        => NotFound()
        case Some(order) => Ok(order)
      }

    case req @ POST -> Root / "orders" =>
      for {
        order       <- req.as[domain.Order]
        placedOrder <- OrderService.apply.placeOrder(order)
        response    <- Ok(placedOrder)
      } yield response
  }

}

object OrderEndpoints {

  def make[F[_]: OrderRepository: Concurrent] = new OrderEndpoints[F].endpoints
}