package controllers

import models.Resident
import models.Place
import models.Location
import models.WatershipWrites1._

import play.api.libs.json.Json

object JsonWritesLab2 {
    
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