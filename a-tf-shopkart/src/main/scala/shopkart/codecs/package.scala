package shopkart

import io.circe.generic.semiauto._
import shopkart.domain._
import io.circe._
import org.http4s._
import cats.effect.Concurrent
import org.http4s.circe._

package object codecs {

  // user
  implicit val userCodecs: Codec[User]                                     = deriveCodec[User]
  implicit def userEntityCoder[F[_]]: EntityEncoder[F, User]               = jsonEncoderOf[F, User]
  implicit def userEntityDecoder[F[_]: Concurrent]: EntityDecoder[F, User] = jsonOf[F, User]
  implicit def usersEntityCoder[F[_]]: EntityEncoder[F, Seq[User]]         = jsonEncoderOf[F, Seq[User]]

  // order
  implicit val orderCodec: Codec[Order]                                      = deriveCodec[Order]
  implicit def orderEntityEncoder[F[_]]: EntityEncoder[F, Order]             = jsonEncoderOf[F, Order]
  implicit def ordersEntitiyEncoder[F[_]]: EntityEncoder[F, Seq[Order]]      = jsonEncoderOf[F, Seq[Order]]
  implicit def orderEntityDecoder[F[_]: Concurrent]: EntityDecoder[F, Order] = jsonOf[F, Order]

}
