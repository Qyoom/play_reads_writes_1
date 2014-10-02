package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

// Writes to Json from Scala (model)
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

// Reads from Json to produce Scala (model)
object PopulationReads1 {
	
	implicit val locationReads: Reads[Location] = (
		(JsPath \ "lat").read[Double] and
		(JsPath \ "long").read[Double]
	)(Location.apply _)
	
	implicit val residentReads: Reads[Resident] = (
		(JsPath \ "name").read[String] and
		(JsPath \ "age").read[Int] and
		(JsPath \ "role").readNullable[String]
	)(Resident.apply _)
	
	implicit val placeReads: Reads[Place] = (
		(JsPath \ "name").read[String] and
		(JsPath \ "location").read[Location] and
		(JsPath \ "residents").read[Seq[Resident]]
	)(Place.apply _)
}


