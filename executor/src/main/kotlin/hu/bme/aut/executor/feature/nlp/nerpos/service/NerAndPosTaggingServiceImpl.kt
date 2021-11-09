package hu.bme.aut.executor.feature.nlp.nerpos.service

import hu.bme.aut.executor.domain.Language
import hu.bme.aut.executor.domain.toLanguageString
import hu.bme.aut.executor.exception.NERFailedException
import hu.bme.aut.executor.exception.PosTaggingFailedException
import hu.bme.aut.executor.feature.nlp.nerpos.dto.NERRequest
import hu.bme.aut.executor.feature.nlp.nerpos.dto.NERResponse
import hu.bme.aut.executor.feature.nlp.nerpos.dto.PosTaggingRequest
import hu.bme.aut.executor.feature.nlp.nerpos.dto.PosTaggingResponse
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
            "$NER_POS_URL/ner/${language.toLanguageString()}",
            NERRequest(text),
            NERResponse::class.java
        )
            ?: throw NERFailedException("NER failed!")
    }

    override fun postTag(language: Language, text: String): PosTaggingResponse {
        return restTemplate.postForObject(
            "$NER_POS_URL/pos/${language.toLanguageString()}",
            PosTaggingRequest(text),
            PosTaggingResponse::class.java
        )
            ?: throw PosTaggingFailedException("POS tagging failed!")
    }

}