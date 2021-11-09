package hu.bme.aut.executor.feature.nlp.sentimentanalysis.service

import hu.bme.aut.executor.domain.Language
import hu.bme.aut.executor.feature.nlp.sentimentanalysis.dto.SentimentAnalysisResponse

interface SentimentAnalysisService {

    fun analyze(language: Language, text: String): SentimentAnalysisResponse

}