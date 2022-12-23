package shopkart.endpoint

import cats._
import cats.effect._
import cats.implicits._
import cats.instances.order
import org.http4s.EntityDecoder
import org.http4s._
import org.http4s.dsl._
import shopkart._
import shopkart.algebra._
import shopkart.codecs._
import shopkart.service._
class OrderEndpoints[F[_]: OrderRepository: Concurrent] private extends Http4sDsl[F] {

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
