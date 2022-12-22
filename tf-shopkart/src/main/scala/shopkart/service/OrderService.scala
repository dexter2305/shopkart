package shopkart.service

import shopkart.algebra.OrderRepository
import shopkart.domain.Order

object OrderService {

  def placeOrder[F[_]: OrderRepository](order: Order): F[Order] =
    implicitly[OrderRepository[F]].save(order)
}
