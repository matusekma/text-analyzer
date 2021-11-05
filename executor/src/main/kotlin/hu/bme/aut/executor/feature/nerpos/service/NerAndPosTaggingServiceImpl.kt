package hu.bme.aut.executor.feature.nerpos.service

import hu.bme.aut.executor.domain.Language
import hu.bme.aut.executor.exception.NERFailedException
import hu.bme.aut.executor.exception.PosTaggingFailedException
import hu.bme.aut.executor.feature.nerpos.dto.NERRequest
import hu.bme.aut.executor.feature.nerpos.dto.NERResponse
import hu.bme.aut.executor.feature.nerpos.dto.PosTaggingRequest
import hu.bme.aut.executor.feature.nerpos.dto.PosTaggingResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class NerAndPosTaggingServiceImpl(
    private val restTemplate: RestTemplate
) : NerAndPosTaggingService {

    @Value("\${application.services.NERposTagging.url}")
    lateinit var NER_POS_URL: String

    override fun ner(language: Language, text: String): NERResponse {
        return restTemplate.postForObject(
            "$NER_POS_URL/ner/${getLanguageString(language)}",
            NERRequest(text),
            NERResponse::class.java
        )
            ?: throw NERFailedException("NER failed!")
    }

    override fun postTag(language: Language, text: String): PosTaggingResponse {
        return restTemplate.postForObject(
            "$NER_POS_URL/pos/${getLanguageString(language)}",
            PosTaggingRequest(text),
            PosTaggingResponse::class.java
        )
            ?: throw PosTaggingFailedException("POS tagging failed!")
    }

    private fun getLanguageString(language: Language) =
        if (language == Language.DE) "german" else "english"

}