import org.http4s.blaze.server.BlazeServerBuilder
import shopkart.config.AppConfig
import pureconfig._
import pureconfig.generic.auto._

val sample1 =
  """ 
  http {
     port = 8080,
     host = 0.0.0.0
  }
  """

// case class Http(port: Int, host: String)

// ConfigSource.string(sample1).load[Http]

val appConfig =
  """
    shopkart {
      http {
        port = 8080,
        host = 0.0.0.0
      },
      db {
        driver = org.xerial.SQLite,
        url = "jdbc:sqlite:./target/shopkart.db",
        user = "",
        pass = ""
      }
    }
  """
case class Db(driver: String, url: String, user: String, pass: String)
case class Http(port: Int, host: String)
case class Config(http: Http, db: Db)

ConfigSource.string(appConfig).at("shopkart").load[Config]

import cats._
import cats.effect._
import cats.implicits._

val r42    = 42.asRight[String]
val some42 = 42.some
val ints   = List(1, 2, 3)

for {
  sn <- some42.asRight[String]
  n  <- r42
} yield ()

val x = ConfigSource
  .resources("application.conf")
  .at("shopkart")
  .load[AppConfig]
  .fold(failures => Left(new Exception(s"$failures")), config => config.asRight[Exception])

def meta[F[_]: Async] =
  for {
    config <- Resource.eval(
      Async[F].fromEither(
        ConfigSource.default
          .at("sopkart")
          .load[AppConfig]
          .fold(failures => Left(new Exception(s"$failures")), _.asRight[Exception])
      )
    )
    server <- BlazeServerBuilder[F]
      .bindHttp(config.http.port, config.http.host)
      .resource
  } yield server

  
