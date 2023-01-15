package shopkart.config

final case class Http private (port: Int, host: String)
final case class Db private (driver: String, url: String, user: String, password: String)
final case class AppConfig(http: Http, db: Db)
