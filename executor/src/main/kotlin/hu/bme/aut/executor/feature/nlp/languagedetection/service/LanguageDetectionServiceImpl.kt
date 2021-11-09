package hu.bme.aut.executor.feature.nlp.languagedetection.service

import hu.bme.aut.executor.exception.LanguageDetectionFailedException
import hu.bme.aut.executor.feature.nlp.languagedetection.dto.LanguageDetectionRequest
import hu.bme.aut.executor.feature.nlp.languagedetection.dto.LanguageDetectionResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class LanguageDetectionServiceImpl(
    private val restTemplate: RestTemplate,
) : LanguageDetectionService {

    @Value("\${application.services.languageDetection.url}")
    lateinit var LANGUAGE_DETECTION_URL: String

    override fun detectLanguage(text: String): LanguageDetectionResponse {
        return restTemplate.postForObject(
            "$LANGUAGE_DETECTION_URL/detect",
            LanguageDetectionRequest(text),
            LanguageDetectionResponse::class.java
        ) ?: throw LanguageDetectionFailedException("Language could not be determined!")
    }


}