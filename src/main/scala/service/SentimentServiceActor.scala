package service

import akka.actor.Actor

class SentimentServiceActor extends Actor with SentimentService {

  def actorRefFactory = context

  def receive = runRoute(routes)
}
