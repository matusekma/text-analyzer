package hu.bme.aut.executor.feature.pipelines.dto

import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.PipelineJobResult

data class PipelineResultResponse(
        val id: Long,
        val uploadId: Long,
        val results: List<PipelineJobResult>
)
