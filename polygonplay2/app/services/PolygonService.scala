package services

import models.{DoublePoint, MyPoint, Polygons}
import java.awt.Polygon

class PolygonService {

  var polygons : List[Polygons] = List[Polygons]()

  def isConvex(list : List[MyPoint]) : String = {
    polygons :+= Polygons(list)

    var setOfPoint : Set[MyPoint] = Set()
    for (point <- list) {
      setOfPoint = setOfPoint ++ Set(point)
    }

    if (setOfPoint.size < 3) {
      return "Not enough different points to build a polygon"
    }

    var gotNegative = false
    var gotPositive = false

    var  numPoints = list.length
    var b : Int = 0
    var c : Int = 0
    for (a <- 0 until numPoints) {
      b = (a + 1) % numPoints
      c = (b + 1) % numPoints

      var abx = list(b).x - list(a).x
      var aby = list(b).y - list(a).y
      var acx = list(c).x - list(a).x
      var acy = list(c).y - list(a).y
      var crossProduct = abx * acy - aby * acx

      if (crossProduct > 0) {
        gotPositive = true
      } else if (crossProduct < 0 ) {
        gotNegative = true
      }

      if(gotNegative && gotPositive) {
        return "polygon is not convex"
      }
    }
    return "polygon is convex"
  }

  def checkPoint(pointToCheck : DoublePoint) : List[Polygons] = {

    var polygonsWithPoint : List[Polygons] = List()

    for (p <- polygons) {
      var awtPolygon = new Polygon()
      for (point <- p.points){
        awtPolygon.addPoint(point.x, point.y)
      }

      if (awtPolygon.contains(pointToCheck.x, pointToCheck.y)) {
        polygonsWithPoint :+= p
      }
    }

    return polygonsWithPoint
  }
}
