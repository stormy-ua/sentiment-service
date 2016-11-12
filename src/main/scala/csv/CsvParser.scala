package csv

import model._
import scalaz.\/

trait CsvParser {
  def parseSentimentSamplesFromResource(resource: String): Throwable \/ SentimentSamples = {
    def parseSample(line: String): SentimentSample = {
      val ls = line.split("\t")
      SentimentSample(ls(0).toInt, ls(1))
    }

    \/.fromTryCatchThrowable[SentimentSamples, Throwable] {
      val stream = getClass.getResourceAsStream(s"/$resource")
      scala.io.Source.fromInputStream(stream).getLines().toList.map(parseSample)
    }
  }
}
