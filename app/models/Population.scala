package models

case class Location(lat: Double, long: Double)
case class Resident(name: String, age: Int, role: Option[String])
case class Place(name: String, location: Location, residents: Seq[Resident])

// I'm not happy about having to have this one-off example,
// So hopefully it will only be temporary while I'm following the tut...
object Place {
	var list: List[Place] = {
	    List(
	    		Place(
		        "Sandleford",
		        Location(51.377797, -1.318965),
		        List()
			),
			Place(
		        "Watership Down",
		        Location(51.235685, -1.309197),
		        List()
			)
	    )
  	}
    
  	def save(place: Place) = {
		list = list ::: List(place)
  	}
}