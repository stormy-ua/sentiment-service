package service

import spray.json.DefaultJsonProtocol

package object json extends DefaultJsonProtocol {
  implicit val sentimentEstimationResponseFormat = jsonFormat1(SentimentEstimationResponse)

}