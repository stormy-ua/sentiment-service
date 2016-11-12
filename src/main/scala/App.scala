package com.kirill.sentimentsservice

import spark._

object Modelling extends SparkSessionBuilder {
  import ml._

  val sentiment1 = trainEstimationFlow("I love the movie")(spark)
  val sentiment2 = estimationFlow("the movie sucked")(spark)

  println(s"Predicted sentiments: $sentiment1, $sentiment2")
}
