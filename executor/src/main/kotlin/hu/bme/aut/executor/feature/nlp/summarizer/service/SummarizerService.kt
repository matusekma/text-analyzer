package hu.bme.aut.executor.feature.nlp.summarizer.service

import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.SummarizerJobRequest
import hu.bme.aut.executor.feature.nlp.summarizer.dto.SummarizerResponse

interface SummarizerService {

    fun summarize(summarizerJobRequest: SummarizerJobRequest): SummarizerResponse
}