package spark

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

trait SparkSessionBuilder {
  implicit lazy val spark = {
    val conf = new SparkConf()
      .setMaster("local")

    val session = SparkSession
      .builder()
      .config(conf)
      .appName("Moview Review Sentiment Analysis")
      .getOrCreate()

    session.sparkContext.setLogLevel("WARN")

    session
  }
}
