package controllers

import play.api.libs.json.Json
import play.api._
import play.api.mvc._

import models.Resident
import models.Place
import models.Location
import models.PopulationWrites2._
import models.User
import models.UserWrites2._

object JsonWritesLab2 extends Controller {
    
    def index = Action {
		Ok("JsonWritesLab2 index!")
	}
    
    /********** USER *******************************/

	def defaultUser = Action { implicit request =>
		Ok(userJson3)
	}
		
	val user1 = User(1, "Sam Spade", List())
	val user2 = User(2, "Gloria Nightengale", List(user1))
	val user3 = User(3, "Kit Carson", List(user1, user2))
	
	// Using UserWrites2
	// THIS DOES NOT WORK, probably due to recursive nature of User.friends
	val userJson3 = Json.toJson(user3)
	
	/*********** POPULATION *************************/
    
    def defaultPopulation = Action { implicit request =>
        Ok(watershipJson)
    }
    
	val waterShip = Place(
		"Watership Down",
		Location(51.235685, -1.309197),
		Seq(
			Resident("Fiver", 4, None),
			Resident("Bigwig", 6, Some("Owsla"))
		)
	)
	
	// Using PopulationWrites2
	val watershipJson = Json.toJson(waterShip)
}