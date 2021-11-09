package hu.bme.aut.executor.feature.jobs.dto.pipelinejobs

import hu.bme.aut.executor.domain.Language

class SummarizerJobRequest(
    val language: Language,
    val text: String,
    val length: Int = 3
)