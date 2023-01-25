package shopkart.endpoints

import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.circe.generic.auto._
import shopkart.domain._

object UserEndpoints {

  private lazy val userEndpoint = infallibleEndpoint.in("users")

  lazy val userPost =
    userEndpoint.post
      .in(jsonBody[User])
      .errorOut(stringBody)
      .out(jsonBody[User])

  lazy val userFindById =
    userEndpoint.get
      .in(path[Int]("id"))
      .errorOut(stringBody)
      .out(jsonBody[Option[User]])

  lazy val userFindByEmail =
    userEndpoint.get
      .in(query[String]("name"))
      .errorOut(stringBody)
      .out(jsonBody[Option[User]])

  lazy val combined = userPost :: userFindById :: userFindByEmail :: Nil

}
