package hu.bme.aut.executor.feature.pipelines.dto

import hu.bme.aut.executor.domain.Pipeline
import hu.bme.aut.executor.domain.Upload

fun PipelineStartRequest.toPipeline(userId: Long, upload: Upload) =
    Pipeline(
        name = name,
        language = language,
        userId = userId,
        upload = upload,
        jobs = jobs.toSet()
    )

fun Pipeline.toPipelineResponse() =
    PipelineResponse(
        id = id!!,
        name = name,
        language = language,
        userId = userId,
        uploadId = upload?.id,
        createdAt = createdAt!!,
        jobs = jobs.toList()
    )