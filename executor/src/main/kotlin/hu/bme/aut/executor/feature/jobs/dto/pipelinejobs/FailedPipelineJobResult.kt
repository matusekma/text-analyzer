package hu.bme.aut.executor.feature.jobs.dto.pipelinejobs

import hu.bme.aut.executor.domain.JobType

class FailedPipelineJobResult(
        type: JobType,
        val error: String) : PipelineJobResult(true, type)