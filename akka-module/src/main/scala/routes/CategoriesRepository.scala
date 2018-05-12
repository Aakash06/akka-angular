package routes

import play.api.libs.json.{Json, OFormat}
import reactivemongo.api.Cursor.FailOnError
import reactivemongo.api.{DefaultDB, MongoConnection, MongoDriver, ReadPreference}
import reactivemongo.bson.BSONObjectID
import reactivemongo.play.json._
import reactivemongo.play.json.collection.JSONCollection

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try

// this is not an unused import contrary to what intellij suggests, do not optimize
import reactivemongo.play.json.BSONFormats.BSONObjectIDFormat

case class CategoryInfo(categoryName: String, subCategory: List[String], _id: BSONObjectID = BSONObjectID.generate)

class CategoriesRepository {
  val mongoUri = "mongodb://localhost:27017/knolx"

  implicit val personWriter: OFormat[CategoryInfo] = Json.format[CategoryInfo]

  val driver = MongoDriver()
  val parsedUri: Try[MongoConnection.ParsedURI] = MongoConnection.parseURI(mongoUri)
  val connection: Try[MongoConnection] = parsedUri.map(driver.connection)

  val futureConnection: Future[MongoConnection] = Future.fromTry(connection)

  def db2: Future[DefaultDB] = futureConnection.flatMap(_.database("knolx"))

  protected def collection: Future[JSONCollection] = db2.map(_.collection("categories"))

  def getCategories: Future[List[CategoryInfo]] = {
    collection.
      flatMap(jsonCollection =>
        jsonCollection.
          find(Json.obj())
          .cursor[CategoryInfo](ReadPreference.Primary)
          .collect[List](-1, FailOnError[List[CategoryInfo]]()))
  }

}