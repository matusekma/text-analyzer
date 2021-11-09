package hu.bme.aut.executor.feature.nlp.summarizer.service

import hu.bme.aut.executor.domain.toLanguageString
import hu.bme.aut.executor.exception.SummarizationFailedException
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.SummarizerJobRequest
import hu.bme.aut.executor.feature.nlp.summarizer.dto.SummarizerRequest
import hu.bme.aut.executor.feature.nlp.summarizer.dto.SummarizerResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class SummarizerServiceImpl(
    private val restTemplate: RestTemplate
) : SummarizerService {

    @Value("\${application.services.summarizer.url}")
    lateinit var SUMMARIZER_URL: String

    override fun summarize(summarizerJobRequest: SummarizerJobRequest): SummarizerResponse {
        return restTemplate.postForObject(
            "$SUMMARIZER_URL/summarize/${summarizerJobRequest.language.toLanguageString()}",
            SummarizerRequest(summarizerJobRequest.text, summarizerJobRequest.length),
            SummarizerResponse::class.java
        )
            ?: throw SummarizationFailedException("Summarization failed!")
    }

}