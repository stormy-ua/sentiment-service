package ml

import model.{ NegativeSentiment, PositiveSentiment, Sentiment, AppReader }
import org.apache.spark.ml.{ PipelineModel }
import scalaz.Reader

trait SentimentEstimator {
  def estimate(pipeline: PipelineModel, text: String): AppReader[Sentiment] = Reader {
    spark ⇒
      val df = spark.createDataFrame(Seq((0, text))).toDF("id", "text")
      val isPositive = pipeline.transform(df)
        .select("prediction")
        .first().getDouble(0)

      if (isPositive == 1.0) PositiveSentiment else NegativeSentiment
  }
}
