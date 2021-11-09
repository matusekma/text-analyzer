package hu.bme.aut.executor.feature.nlp.punctuationrestoration.service

import hu.bme.aut.executor.exception.PunctuationRestorationFailedException
import hu.bme.aut.executor.feature.nlp.punctuationrestoration.dto.PunctuationRestorationRequest
import hu.bme.aut.executor.feature.nlp.punctuationrestoration.dto.PunctuationRestorationResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class PunctuationRestorationServiceImpl(
    private val restTemplate: RestTemplate
) : PunctuationRestorationService {

    @Value("\${application.services.punctuationRestoration.url}")
    lateinit var PUNCTUATION_RESTORATION_URL: String

    override fun punctuate(punctuationRestorationRequest: PunctuationRestorationRequest): PunctuationRestorationResponse {
        return restTemplate.postForObject(
            "$PUNCTUATION_RESTORATION_URL/punctuate",
            PunctuationRestorationRequest(punctuationRestorationRequest.text),
            PunctuationRestorationResponse::class.java
        ) ?: throw PunctuationRestorationFailedException("Punctuation restoration failed!")
    }

}