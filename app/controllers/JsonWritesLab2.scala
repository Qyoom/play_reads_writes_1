package controllers

import models.Resident
import models.Place
import models.Location
import models.PopulationWrites1._
import play.api.libs.json.Json
import play.api._
import play.api.mvc._

object JsonWritesLab2 extends Controller {
    
    def index = Action {
		Ok("JsonWritesLab2 index!")
	}
    
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
	
	// Using PopulationWrites1
	val watershipJson = Json.toJson(waterShip)
}