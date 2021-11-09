package hu.bme.aut.executor.feature.pipelines.dto

import hu.bme.aut.executor.domain.JobType
import hu.bme.aut.executor.domain.Language
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class PipelineStartRequest(
        @NotBlank
        val name: String,
        @NotNull
        val uploadId: Long,
        @NotNull
        val language: Language,
        @NotNull
        val jobs: List<JobType>,
        val options: Map<JobType, Map<String, String>>?
)
