package models

import play.api.libs.json.Json

case class DoublePoint (val x : Double, val y :Double)

object DoublePoint {
  implicit val format = Json.format[DoublePoint]
}
