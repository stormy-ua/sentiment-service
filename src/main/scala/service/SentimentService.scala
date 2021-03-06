package service

import journal.Logger
import model.{ NegativeSentiment, PositiveSentiment }
import org.apache.spark.sql.SparkSession
import spray.http.MediaTypes._
import spray.routing._
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import spray.httpx.marshalling.MetaMarshallers._
import scala.concurrent.{ Promise, Future, ExecutionContext }
import ExecutionContext.Implicits.global
import scalaz.{ -\/, \/- }
import scalaz.std.scalaFuture._

trait SentimentService extends HttpService {
  import ml._
  import spark._
  import json._

  val routes =
    respondWithMediaType(`application/json`) {
      post {
        path("estimate") {
          entity(as[String]) { text ⇒ ctx ⇒
            estimationFlow(text)(spark) match {
              case \/-(PositiveSentiment) ⇒ ctx.complete(SentimentEstimationResponse("positive"))
              case \/-(NegativeSentiment) ⇒ ctx.complete(SentimentEstimationResponse("negative"))
              case -\/(e)                 ⇒ ctx.failWith(e)
            }
          }
        } ~
          path("train") {
            entity(as[String]) { text ⇒
              complete {
                trainingFlow(modelName)(spark)
                "done"
              }
            }
          }
      }
    }
}
