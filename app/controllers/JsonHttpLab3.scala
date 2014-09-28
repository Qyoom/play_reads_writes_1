package controllers

import play.api._
import play.api.mvc._

import play.api.libs.json._
import play.api.libs.json.JsValue
import play.api.libs.json.JsObject
import play.api.libs.json.Json._
import play.api.libs.json.Json
import play.api.libs.functional.syntax._

import models.PopulationWrites1._
import models.PopulationReads1._
import models.Place

object JsonHttpLab3 extends Controller {
    
    def listPlaces = Action {
        // Using implicit Writes[T] from PopulationWrites1
    		val json = Json.toJson(Place.list)
		Ok(json)
    }
}

