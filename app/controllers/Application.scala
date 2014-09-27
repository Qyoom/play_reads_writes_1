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
import models.User._

object Application extends Controller {

	def index = Action {
		Ok(views.html.index("Your new application is ready."))
	}
	
	implicit val userWrites = new Writes[User] {    
	    def writes(u: User): JsValue = JsObject(List(
	    		"id" -> JsNumber	(u.id),
	    		"name" -> JsString(u.name),
	    		"friends" -> JsArray(u.friends.map(fr => JsObject(List(
		        "id" -> JsNumber(fr.id),
		        "name" -> JsString(fr.name))
	    		))))
	    )
	}
	
	val user1 = User(1, "Sam Spade", List())
	val user2 = User(2, "Gloria Nightengale", List(user1))
	val user3 = User(3, "Kit Carson", List(user1, user2))
	
	val userJson3 = Json.toJson(user3)
}



