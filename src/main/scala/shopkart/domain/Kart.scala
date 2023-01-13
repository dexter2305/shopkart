package shopkart.domain

case class Kart(id: Int, userId: Int, items: Seq[KartItem])