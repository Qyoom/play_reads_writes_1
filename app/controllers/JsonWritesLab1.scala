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
import models.PopulationWrites2._
import models.UserWrites._

object JsonWritesLab1 extends Controller {
    
	def index = Action {
		Ok("JsonWritesLab1 index!")
	}

	def defaultUser = Action { implicit request =>
		Ok(userJson3)
	}
	
	/********** USER *******************************/
	
	val user1 = User(1, "Sam Spade", List())
	val user2 = User(2, "Gloria Nightengale", List(user1))
	val user3 = User(3, "Kit Carson", List(user1, user2))
	
	// Using UserWrites
	val userJson3 = Json.toJson(user3)
	
	/*********** WATERSHIP *************************/
	
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



