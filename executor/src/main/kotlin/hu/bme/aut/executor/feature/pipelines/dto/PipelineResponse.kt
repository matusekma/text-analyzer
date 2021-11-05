package hu.bme.aut.executor.feature.pipelines.dto

import hu.bme.aut.executor.domain.JobType
import hu.bme.aut.executor.domain.Language
import java.time.OffsetDateTime

class PipelineResponse(
    val id: Long,
    val name: String,
    val language: Language,
    val userId: Long,
    val uploadId: Long?,
    val createdAt: OffsetDateTime,
    val jobs: List<JobType>
)