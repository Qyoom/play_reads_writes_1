package controllers

import play.api._
import play.api.mvc._

import play.api.libs.json._
import play.api.libs.json.JsValue
import play.api.libs.json.JsObject
import play.api.libs.json.Format
import play.api.libs.json.JsNumber
import play.api.libs.json.JsString
import play.api.libs.json.JsArray
import play.api.libs.json.Json._
import play.api.libs.json.Json

import models.User
import models._

object JsonWritesLab1 extends Controller {

	def index = Action {
		Ok("")
	}
	
	/********** USER *******************************/
	
	implicit val userWrites = new Writes[User] {    
	    def writes(u: User) = Json.obj(
	    		"id" -> u.id,
	    		"name" -> u.name,
	    		"friends" -> u.friends.map(fr => Json.obj(
		        "id" -> fr.id,
		        "name" -> fr.name
	    		))
    		)
	}
	
	val user1 = User(1, "Sam Spade", List())
	val user2 = User(2, "Gloria Nightengale", List(user1))
	val user3 = User(3, "Kit Carson", List(user1, user2))
	
	val userJson3 = Json.toJson(user3)
	
	/*********** WATERSHIP *************************/
	
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
	
	val waterShip = Place(
		"Watership Down",
		Location(51.235685, -1.309197),
		Seq(
			Resident("Fiver", 4, None),
			Resident("Bigwig", 6, Some("Owsla"))
		)
	)
	
	val watershipJson = Json.toJson(waterShip)
}



