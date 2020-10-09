package models

import play.api.libs.json.{Json, Writes}

case class MyPoint (val x :Int, val y : Int)

object MyPoint {
  implicit val format = Json.format[MyPoint]

  implicit val  pointWrites = new Writes[MyPoint] {
    def writes(myPoint: MyPoint) = Json.obj(
      "x" -> myPoint.x,
      "y" -> myPoint.y
    )
  }
}
