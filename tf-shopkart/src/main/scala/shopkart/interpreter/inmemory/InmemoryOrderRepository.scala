package shopkart.interpreter.inmemory

import shopkart.algebra.OrderRepository
import shopkart.domain.Order
import scala.collection.concurrent.TrieMap
import cats._
import cats.implicits._

class InmemoryOrderRepository[F[_]: Applicative] private extends OrderRepository[F] {

  private val store = TrieMap.empty[Int, Order]

  override def save(order: Order): F[Order] = {
    store.put(order.id, order)
    order.pure[F]
  }

  override def findById(id: Int): F[Option[Order]] =
    store.get(id).pure[F]

  override def findByUserId(userid: Int): F[Seq[Order]] =
    store.values.filter(order => order.userid === userid).toSeq.pure[F]

}

object InmemoryOrderRepository {

  def make[F[_]: Applicative] = new InmemoryOrderRepository[F]
}
