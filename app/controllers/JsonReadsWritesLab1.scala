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
import models.PopulationWrites1._
import models.PopulationReads1._
import models.UserWrites._

/**
 * Writing to Json from model
 */
object JsonReadsWritesLab1 extends Controller {
    
	def index = Action {
		Ok("JsonWritesLab1 index!")
	}
	
	/********** USER *******************************/

	def defaultUser = Action { implicit request =>
		Ok(userJson3)
	}
		
	val user1 = User(1, "Sam Spade", List())
	val user2 = User(2, "Gloria Nightengale", List(user1))
	val user3 = User(3, "Kit Carson", List(user1, user2))
	
	// Using UserWrites
	val userJson3 = Json.toJson(user3)
	
	/* Json path navigation and utilities */
	println("JsonWritesLab1 USER name: " + userJson3 \ "name")
	println("id: " + userJson3 \ "id")
	println("friends: " + userJson3 \ "friends")
	// \\ is not really recursive, it doesn't understand nested relationships
	println("friends and friends of friends: " + userJson3 \\ "friends")
	println("All names in this tree: " + userJson3 \\ "name")
	val bestFriend = (userJson3 \ "friends")(0)
	println("Best friend: " + bestFriend)
	val secondBestFriend = (userJson3 \ "friends")(1)
	println("Second best friend: " + secondBestFriend)
	
	println(userJson3)
	println(Json.stringify(userJson3))
	println(Json.prettyPrint(userJson3))
	
	/*********** POPULATION *************************/
	
	// Producing Json for response with Writes[T]
	def getDefaultPopulation = Action { implicit request =>
        Ok(watershipJson) // Returning Json
    }
	
	// Model instance
	val waterShip = Place(
		"Watership Down",
		Location(51.235685, -1.309197),
		Seq(
			Resident("Fiver", 4, None),
			Resident("Bigwig", 6, Some("Owsla"))
		)
	)
	
	// Uses PopulationWrites1
	val watershipJson = Json.toJson(waterShip)
	
	// Consuming Json to produce model with PopulationReads1
	val watershipModel = watershipJson.validate[Place]
	watershipModel match {
	    case s:JsSuccess[Place] => println("watershipModel: " + s)
	    case e:JsError => println("Errors: " + JsError.toFlatJson(e).toString)
	}
	
	def putPlace = Action { implicit request =>
	    request.body.asJson.map { json =>
	    		Ok
	    }.getOrElse {
			BadRequest("Expecting Json data")
		}
	}
}



