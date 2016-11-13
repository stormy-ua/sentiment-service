import org.apache.spark.sql.SparkSession
import scalaz.{ \/, Kleisli, Reader }

package object model {
  type SentimentSamples = List[SentimentSample]
  type AppError[+A] = Throwable \/ A
  type AppReader[A] = Kleisli[AppError, SparkSession, A]

  object AppReader {
    def apply[A](f: SparkSession ⇒ AppError[A]) = Kleisli[AppError, SparkSession, A](f)
  }
}
