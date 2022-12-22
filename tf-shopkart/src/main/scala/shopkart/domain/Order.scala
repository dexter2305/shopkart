package shopkart.domain

import io.circe._
import io.circe.generic.semiauto._

final case class Order(id: Int, userid: Int)

object Order {

  // todo: not convinced with circe reference in domain. think of externalizing codecs for domain.
  // def orderDecoderControlled: Decoder[Order] = Decoder.forProduct2[Order, Int, Int]("id", "userid")((orderid, userid) => Order(orderid, userid))
  implicit def orderDecoder: Decoder[Order] = deriveDecoder[Order]
  implicit def orderEncoder: Encoder[Order] = deriveEncoder[Order]

}
