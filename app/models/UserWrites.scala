package models

import play.api.libs.json.Writes
import play.api.libs.json.Json

object UserWrites {
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
}