package ml

import model.{ AppReader }
import org.apache.spark.ml.{ PipelineModel }
import scalaz.\/

trait PipelineRepository {
  private val baseDir = "/Users/kirill/Documents/Projects/sentiment-service/src/main/resources"

  def save(pipeline: PipelineModel, fileName: String): AppReader[Unit] = AppReader[Unit] {
    spark ⇒
      \/.fromTryCatchNonFatal {
        pipeline.write.overwrite().save(s"$baseDir/$fileName")
      }
  }

  def load(fileName: String): AppReader[PipelineModel] = AppReader[PipelineModel] {
    spark ⇒
      \/.fromTryCatchNonFatal {
        PipelineModel.load(s"$baseDir/$fileName")
      }
  }
}
