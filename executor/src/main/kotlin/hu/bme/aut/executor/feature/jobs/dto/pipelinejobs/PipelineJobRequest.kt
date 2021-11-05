package hu.bme.aut.executor.feature.jobs.dto.pipelinejobs

import hu.bme.aut.executor.domain.JobType
import hu.bme.aut.executor.domain.Language
import hu.bme.aut.executor.domain.Upload

class PipelineJobRequest(
        val upload: Upload,
        val language: Language,
        val job: JobType
)