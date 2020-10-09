package models

import play.api.libs.json.{Json, Writes}

case class Polygons (val points : List[MyPoint])

object Polygons {
  implicit val format = Json.format[Polygons]



}
