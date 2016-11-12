package spark

import org.apache.spark.ml.linalg.SparseVector
import org.apache.spark.sql._
import org.apache.spark.sql.functions._

trait Slicing {
  def getVectorElem(index: Int) = udf {
    (x: SparseVector) ⇒ if (index < x.size) x(index) else 0
  }

  def sliceVectorCol(df: DataFrame, colName: String, vectorSize: Int): DataFrame =
    Range(0, vectorSize).foldLeft(df) {
      (df, i) ⇒ df.withColumn(s"${colName}$i", getVectorElem(i)(df(colName)))
    }
}
