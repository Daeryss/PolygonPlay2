package controllers

import javax.inject.Inject
import models.{DoublePoint, MyPoint, Polygons}
import play.api.libs.json.{Json, Writes}
import play.api.mvc.InjectedController
import services.PolygonService

class PolygonController @Inject() (polygonService: PolygonService) extends InjectedController {

  def greeting() = Action {
    Ok("Polygon service again say 'Hello'")
  }

  def addPolygon = Action(parse.json) { implicit request =>
    val points:List[MyPoint] = request.body.as[List[MyPoint]]
    var len = points.length
    Ok(polygonService.isConvex(points:List[MyPoint]))

  }

  def checkThisPoint = Action(parse.json) { implicit request =>
    val doublePoint = request.body.as[DoublePoint]
    Ok(Json.toJson(polygonService.checkPoint(doublePoint)))
  }

  def allPolygons() = Action {
    Ok(Json.toJson(polygonService.polygons))
  }

}
