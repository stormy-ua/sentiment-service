package ml

import model.AppReader
import org.apache.spark.ml.{ PipelineModel }

import scalaz.Reader

trait PipelineRepository {
  private val baseDir = "/Users/kirill/Documents/Projects/sentiments-service/src/main/resources"

  def save(pipeline: PipelineModel, fileName: String): AppReader[Unit] = Reader {
    spark ⇒ pipeline.write.overwrite().save(s"$baseDir/$fileName")
  }

  def load(fileName: String): AppReader[PipelineModel] = Reader {
    spark ⇒ PipelineModel.load(s"$baseDir/$fileName")
  }
}
