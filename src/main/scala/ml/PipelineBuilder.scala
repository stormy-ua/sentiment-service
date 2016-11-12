package ml

import model.AppReader
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.feature._

import scalaz.Reader

trait PipelineBuilder {
  def buildLogRegr(inputColName: String, labelColName: String): AppReader[Pipeline] = Reader {
    spark ⇒
      val tokenizer = new Tokenizer()
        .setInputCol(inputColName)
        .setOutputCol("words")

      val stopWordsRemover = new StopWordsRemover()
        .setInputCol("words")
        .setOutputCol("words_filtered")

      /*val countVectorizer = new CountVectorizer()
        .setInputCol("words_filtered")
        .setOutputCol("rawFeatures")
        .setVocabSize(1000)*/

      val hashingTF = new HashingTF()
        .setInputCol("words")
        .setOutputCol("rawFeatures")
        .setNumFeatures(700)

      val idf = new IDF()
        .setInputCol("rawFeatures")
        .setOutputCol("features")

      val lr = new LogisticRegression()
        .setMaxIter(1000)
        .setRegParam(0.05)
        .setFeaturesCol("features")
        .setLabelCol(labelColName)

      val pipeline = new Pipeline()
        .setStages(Array(tokenizer, stopWordsRemover, hashingTF, idf, lr))

      pipeline
  }
}
