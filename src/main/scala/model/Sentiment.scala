package model

sealed trait Sentiment

object NegativeSentiment extends Sentiment
object PositiveSentiment extends Sentiment