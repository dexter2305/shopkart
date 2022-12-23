package shopkart.domain

import io.circe._
import io.circe.generic.semiauto._

final case class User(id: Int, name: String, email: String)

object User {

  // todo: not convinced with circe reference in domain. think of externalizing codecs for domain.
  implicit def usercodec: Codec[User] = deriveCodec[User]
}
