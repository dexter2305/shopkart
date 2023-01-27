package shopkart.endpoints

import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.circe.generic.auto._
import shopkart.domain._

object UserEndpoints {

  private lazy val userEndpoint = infallibleEndpoint
  .in("users")
  .errorOut(statusCode)

  lazy val userPost =
    userEndpoint.post
      .in(jsonBody[User])
      .out(jsonBody[Int])

  lazy val userFindById =
    userEndpoint.get
      .in(path[Int]("id"))
      .out(jsonBody[User])

  lazy val userFindByEmail =
    userEndpoint.get
      .in(query[String]("email"))
      .out(jsonBody[User])

  lazy val combined = userPost :: userFindById :: userFindByEmail :: Nil

}
