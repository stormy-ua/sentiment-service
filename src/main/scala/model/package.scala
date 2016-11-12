import org.apache.spark.sql.SparkSession
import scalaz.Reader

package object model {
  type SentimentSamples = List[SentimentSample]
  type AppReader[A] = Reader[SparkSession, A]
}
