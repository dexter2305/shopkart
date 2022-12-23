package shopkart

import io.circe._
import io.circe.generic.semiauto._
import org.http4s._
import org.http4s.circe._
import shopkart.domain._
import cats.effect.Concurrent
package object codecs {

  // user
  implicit val userCodec: Codec[User]                                      = deriveCodec[User]
  implicit def userEntityEncoder[F[_]]: EntityEncoder[F, User]             = jsonEncoderOf[F, User]
  implicit def userEntityDecoder[F[_]: Concurrent]: EntityDecoder[F, User] = jsonOf[F, User]
}
