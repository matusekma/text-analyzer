package hu.bme.aut.executor.feature.jobs.dto.pipelinejobs

import hu.bme.aut.executor.domain.JobType

class SummarizerJobResult(
    val results: List<String>
): PipelineJobResult(type = JobType.SUMMARIZATION)
