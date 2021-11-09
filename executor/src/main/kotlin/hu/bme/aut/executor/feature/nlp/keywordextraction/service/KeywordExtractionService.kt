package hu.bme.aut.executor.feature.nlp.keywordextraction.service

import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.KeywordExtractionJobRequest
import hu.bme.aut.executor.feature.nlp.keywordextraction.dto.KeywordExtractionResponse

interface KeywordExtractionService {

    fun extract(keywordExtractionJobRequest: KeywordExtractionJobRequest): KeywordExtractionResponse

}