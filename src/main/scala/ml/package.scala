import data.DataRepository
import org.apache.spark.sql.DataFrame

package object ml extends DataRepository
    with PipelineBuilder
    with SentimentEstimator
    with PipelineRepository {

  val modelName = "logRegModel"
  lazy val trainedModel = load(modelName)

  def trainingFlow(modelName: String) = for {
    trainDf ← loadMoviewReviewsDataset
    pipeline ← buildLogRegr("text", "sentiment")
    model = pipeline.fit(trainDf)
    _ ← save(model, modelName)
  } yield model

  def estimationFlow(text: String) = for {
    model ← trainedModel
    sentiment ← estimate(model, text)
  } yield sentiment

  def trainEstimationFlow(text: String) = for {
    _ ← trainingFlow(modelName)
    sentiment ← estimationFlow(text)
  } yield sentiment

  def calcPredictionCrossTab = for {
    df ← loadMoviewReviewsDataset
    Array(train, test) = df.randomSplit(Array(0.85, 0.15))
    pipeline ← buildLogRegr("text", "sentiment")
    model = pipeline.fit(train)
    trainWithPred = model.transform(test)
  } yield trainWithPred.stat.crosstab("sentiment", "prediction")
}
