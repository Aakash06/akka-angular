package routes

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.codesquad.util.LoggerUtil
import play.api.libs.json.{Json, OFormat}

// this is not an unused import contrary to what intellij suggests, do not optimize
import reactivemongo.play.json.BSONFormats.BSONObjectIDFormat

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}

object BlogAnalyticsHttpServer extends App {

  implicit val system: ActorSystem = ActorSystem("FileSystem")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  implicit val categoryInfo: OFormat[CategoryInfo] = Json.format[CategoryInfo]

  val logger = LoggerUtil.logger

  val concurrency = Runtime.getRuntime.availableProcessors() * 4

  val a = new CategoriesRepository

  val b = a.getCategories.map{ df =>
    Json.toJson(df).toString()
  }

  val route =
    path("hello") {
      get {
        complete(b)
      }
    }

  val port = 8080

  val bindingFuture = Http().bindAndHandle(route, "localhost", port)

  bindingFuture.onComplete {
    case Success(_) ⇒ logger.info("======= Service running success =========")
    case Failure(e) ⇒
      logger.info(s"Exception Binding failed with ${e.getMessage}")
      system.terminate()
  }
}

