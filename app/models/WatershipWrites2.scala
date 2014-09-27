package models

import play.api.libs.json.Writes
import play.api.libs.json.Json

object WatershipWrites2 {
	implicit val locationWrites = new Writes[Location] {
	    def writes(location: Location) = Json.obj(
	        "lat" -> location.lat,
	        "lon" -> location.lon
	    )
	}
	
	implicit val residentWrites = new Writes[Resident] {
	    def writes(resident: Resident) = Json.obj(
	    		"name" -> resident.name,
	    		"age"  -> resident.age,
	    		"role" -> resident.role
	    )
	}
	
	implicit val placeWrites = new Writes[Place] {
	    def writes(place: Place) = Json.obj(
	    		"name" -> place.name,
	    		"location" -> place.location,
	    		"residents" -> place.residents
	    )
	}
}