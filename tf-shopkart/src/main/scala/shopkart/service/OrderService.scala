package shopkart.service

import shopkart.algebra.OrderRepository
import shopkart.domain.Order

class OrderService[F[_]: OrderRepository] private {

  def placeOrder(order: Order): F[Order] =
    implicitly[OrderRepository[F]].save(order)

  def findOrdersById(userid: Int): F[Seq[Order]] =
    implicitly[OrderRepository[F]].findByUserId(userid)

  def findOrderById(orderId: Int): F[Option[Order]] =
    implicitly[OrderRepository[F]].findById(orderId)
}

object OrderService {


  def apply[F[_]: OrderRepository] = new OrderService[F]

}
