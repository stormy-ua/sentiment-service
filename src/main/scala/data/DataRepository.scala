package data

import csv._
import model.AppReader
import org.apache.spark.sql.DataFrame
import scalaz.Reader

trait DataRepository {
  def loadMoviewReviewsDataset: AppReader[DataFrame] = {
    Reader { spark ⇒
      {
        val train = parseSentimentSamplesFromResource("training.txt")
        spark.createDataFrame(train.toOption.get).toDF("sentiment", "text")
      }
    }
  }
}
