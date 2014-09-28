package lab

import play.api.libs.json._

object json_lab_1 {

    // Json.parse
	val json1: JsValue = Json.parse("""
	{
	  "name" : "Watership Down",
	  "location" : {
	    "lat" : 51.235685,
	    "long" : -1.309197
	  },
	  "residents" : [ {
	    "name" : "Fiver",
	    "age" : 4,
	    "role" : null
	  }, {
	    "name" : "Bigwig",
	    "age" : 6,
	    "role" : "Owsla"
	  } ]
	}
	""")
	
	// JsValue, JsString, JsObject, JsArray, JsNumber, JsNull
	val json2: JsValue = JsObject(Seq(
	  "name" -> JsString("Watership Down"),
	  "location" -> JsObject(Seq("lat" -> JsNumber(51.235685), "long" -> JsNumber(-1.309197))),
	  "residents" -> JsArray(Seq(
	    JsObject(Seq(
	      "name" -> JsString("Fiver"),
	      "age" -> JsNumber(4),
	      "role" -> JsNull
	    )),
	    JsObject(Seq(
	      "name" -> JsString("Bigwig"),
	      "age" -> JsNumber(6),
	      "role" -> JsString("Owsla")
	    ))
	  ))
	)) 
	
	// Json.obj
	// Json.arr
	val json3: JsValue = Json.obj(
	  "name" -> "Watership Down",
	  "location" -> Json.obj("lat" -> 51.235685, "long" -> -1.309197),
	  "residents" -> Json.arr(
	    Json.obj(
	      "name" -> "Fiver",
	      "age" -> 4,
	      "role" -> JsNull
	    ),
	    Json.obj(
	      "name" -> "Bigwig",
	      "age" -> 6,
	      "role" -> "Owsla"
	    )
	  )
	)
	
	// implicit Writes[T] converters for basic types...
	// Json.toJson
	val jsonString = Json.toJson("Fiver")
	val jsonNumber = Json.toJson(4)
	val jsonBoolean = Json.toJson(false)
	
	// ... and collections of basic types
	// Json.toJson(Seq())
	val jsonArrayOfInts = Json.toJson(Seq(1, 2, 3, 4))
	val jsonArrayOfStrings = Json.toJson(List("Fiver", "Bigwig"))
	
	// implicit Reads[T]
	// As with Writes, the JSON API provides Reads for basic types.
	val name = (json3 \ "name").as[String] // throws a JsResultException if the path is not found or the conversion is not possible
    val names = (json3 \\ "name").map(_.as[String])
    // safer
    val nameOption = (json3 \ "name").asOpt[String]
    val bogusOption = (json3 \ "bogus").asOpt[String] // no exception, but any error information is lost
    
    // implicit Reads with validation
    val validatedName = (json3 \ "name").validate[String]
	val nameResult:String = validatedName match {
	    case s:JsSuccess[String] => s.toString
	    case e:JsError => JsError.toFlatJson(e).toString
	}
	// Fallback value
	val nameResult2:String = (json3 \ "name").validate[String].getOrElse("undefined")
	// map
	val upper = nameResult.map(_.toUpper)

	// fold DOES NOT WORK!!!
	/*val nameOption2: Option[String] = nameOption.fold(
		invalid = {
			fieldErrors => fieldErrors.foreach(x => {
				println("field: " + x._1 + ", errors: " + x._2)
			})
			None
		},
		valid = { 
			name => Some(name)
		}
	)*/
}




