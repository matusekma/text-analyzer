package hu.bme.aut.executor.feature.jobs.dto.pipelinejobs

class SentenceSentimentResult(
    val sentence: String,
    val sentiment: String,
    val confidence: Float
)