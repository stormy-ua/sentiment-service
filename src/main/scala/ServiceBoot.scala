import akka.actor.{ Props, ActorSystem }
import akka.io.IO
import akka.util.Timeout
import service.SentimentServiceActor
import spray.can.Http
import scala.concurrent.duration._
import akka.pattern.ask

object ServiceBoot extends App {

  implicit val system = ActorSystem("on-spray-can")

  val service = system.actorOf(Props[SentimentServiceActor], "sentiment-service")

  implicit val timeout = Timeout(5.seconds)
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)
}