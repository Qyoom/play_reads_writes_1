package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

object PopulationWrites1 {

	implicit val locationWrites: Writes[Location] = (
		(JsPath \ "lat").write[Double] and
		(JsPath \ "long").write[Double]
	)(unlift(Location.unapply))
	
	implicit val residentWrites: Writes[Resident] = (
		(JsPath \ "name").write[String] and
		(JsPath \ "age").write[Int] and
		(JsPath \ "role").writeNullable[String]
	)(unlift(Resident.unapply))
	
	implicit val placeWrites: Writes[Place] = (
		(JsPath \ "name").write[String] and
		(JsPath \ "location").write[Location] and
		(JsPath \ "residents").write[Seq[Resident]]
	)(unlift(Place.unapply))
}