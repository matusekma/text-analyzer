package hu.bme.aut.executor.feature.nlp.keywordextraction.service

import hu.bme.aut.executor.domain.toLanguageString
import hu.bme.aut.executor.exception.KeywordExtractionFailedException
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.KeywordExtractionJobRequest
import hu.bme.aut.executor.feature.nlp.keywordextraction.dto.KeywordExtractionRequest
import hu.bme.aut.executor.feature.nlp.keywordextraction.dto.KeywordExtractionResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class KeywordExtractionServiceImpl(
    private val restTemplate: RestTemplate,
) : KeywordExtractionService {

    @Value("\${application.services.keywordExtraction.url}")
    lateinit var KEYWORD_EXTRACTION_URL: String

    override fun extract(keywordExtractionJobRequest: KeywordExtractionJobRequest): KeywordExtractionResponse {
        return restTemplate.postForObject(
            "$KEYWORD_EXTRACTION_URL/extract/${keywordExtractionJobRequest.language.toLanguageString()}",
            KeywordExtractionRequest(keywordExtractionJobRequest.text),
            KeywordExtractionResponse::class.java
        ) ?: throw KeywordExtractionFailedException("Keyword extraction failed!")
    }
}