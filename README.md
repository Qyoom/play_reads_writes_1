Laboratory for experimenting with Play's implicit Reads and Writes.

Postman API examples:
https://www.getpostman.com/collections/e23558e1324e7a1333ec

Try these two API calls:
get list of Places as Json
put The Shire (Place)
get list of Places as Json (again to see addition of previous)

Corresponds to these Play routes:
GET		/places					controllers.JsonHttpLab3.listPlaces
POST		/places					controllers.JsonHttpLab3.savePlace

The actual Reads and Writes are in this Scala file:
models/PopulationReadsWrites1.scala
