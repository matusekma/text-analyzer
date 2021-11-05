package hu.bme.aut.executor.feature.sentimentanalysis.dto

class SentenceSentimentResponse(
    val sentence: String,
    val sentiment: String,
    val confidence: Float
)
