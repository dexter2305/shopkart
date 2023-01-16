package shopkart

import io.circe._
import shopkart.domain._
import cats.effect._
import org.http4s._
import io.circe.generic.semiauto._
import org.http4s.circe._
package object codecs {

  implicit def userCodec: Codec[User]                                      = deriveCodec[User]
  implicit def userEntityDecoder[F[_]: Concurrent]: EntityDecoder[F, User] = jsonOf[F, User]
  implicit def userEntityEncoder[F[_]]: EntityEncoder[F, User]             = jsonEncoderOf[F, User]

}
