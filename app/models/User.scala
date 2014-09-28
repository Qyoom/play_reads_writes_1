package models
import play.api.libs.json.Writes
import play.api.libs.json.Json

case class User(id: Long, name: String, friends: Seq[User])