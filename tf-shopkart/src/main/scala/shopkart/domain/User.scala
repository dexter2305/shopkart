package shopkart.domain

final case class User(id: Int, name: String, email: String)

final case class Order(id: Int, userid: Int)