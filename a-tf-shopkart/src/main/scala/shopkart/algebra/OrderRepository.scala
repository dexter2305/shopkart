package shopkart.algebra

import shopkart.domain._

trait OrderRepository[F[_]] {

  def save(order: Order): F[Order]
  def findById(id: Int): F[Option[Order]]
  def findByUserId(id: Int): F[Seq[Order]]

}
