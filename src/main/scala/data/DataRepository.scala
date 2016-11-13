package data

import csv._
import model.AppReader
import org.apache.spark.sql.DataFrame
import scalaz.\/

trait DataRepository {
  def loadMoviewReviewsDataset: AppReader[DataFrame] = AppReader[DataFrame] {
    spark ⇒
      \/.fromTryCatchNonFatal {
        val train = parseSentimentSamplesFromResource("training.txt")
        spark.createDataFrame(train.toOption.get).toDF("sentiment", "text")
      }
  }
}
