import data.DataRepository

package object ml extends DataRepository
    with PipelineBuilder
    with SentimentEstimator
    with PipelineRepository {
  val trainingFlow = for {
    trainDf ← loadMoviewReviewsDataset
    pipeline ← buildLogRegr("text", "sentiment")
    model = pipeline.fit(trainDf)
    _ ← save(model, "logRegModel")
  } yield model

  def estimationFlow(text: String) = for {
    model ← load("logRegModel")
    sentiment ← estimate(model, text)
  } yield sentiment

  def trainEstimationFlow(text: String) = for {
    _ ← trainingFlow
    sentiment ← estimationFlow(text)
  } yield sentiment
}
