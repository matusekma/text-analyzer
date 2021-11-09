package hu.bme.aut.executor.feature.jobs.dto.pipelinejobs

import hu.bme.aut.executor.domain.Language

class KeywordExtractionJobRequest(
    val language: Language,
    val text: String
)