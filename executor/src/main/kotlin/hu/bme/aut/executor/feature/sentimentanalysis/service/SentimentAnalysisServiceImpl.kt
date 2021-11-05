package hu.bme.aut.executor.feature.sentimentanalysis.service

import hu.bme.aut.executor.domain.Language
import hu.bme.aut.executor.exception.SentimentAnalysisFailedException
import hu.bme.aut.executor.feature.sentimentanalysis.dto.SentimentAnalysisRequest
import hu.bme.aut.executor.feature.sentimentanalysis.dto.SentimentAnalysisResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class SentimentAnalysisServiceImpl(
    private val restTemplate: RestTemplate
) : SentimentAnalysisService {

    @Value("\${application.services.sentimentAnalysis.url}")
    lateinit var SENTIMENT_ANALYSIS_URL: String

    override fun analyze(language: Language, text: String): SentimentAnalysisResponse {
        return restTemplate.postForObject(
            "$SENTIMENT_ANALYSIS_URL/analyze/${if(language == Language.DE) "german" else "english"}",
            SentimentAnalysisRequest(text),
            SentimentAnalysisResponse::class.java
        )
            ?: throw SentimentAnalysisFailedException("Sentiment Analysis failed!")
    }
}