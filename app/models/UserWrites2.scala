package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

object UserWrites2 {
    // This is not working, probably  because of recursion on friends
	implicit val userWrites: Writes[User] = (
		(JsPath \ "id").write[Long] and
		(JsPath \ "name").write[String] and
		(JsPath \ "friends").write[Seq[User]]
	)(unlift(User.unapply))
}