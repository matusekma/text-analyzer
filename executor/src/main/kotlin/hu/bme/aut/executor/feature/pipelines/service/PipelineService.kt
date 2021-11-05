package hu.bme.aut.executor.feature.pipelines.service

import hu.bme.aut.executor.feature.pipelines.dto.PipelineResponse
import hu.bme.aut.executor.feature.pipelines.dto.PipelineResultResponse
import hu.bme.aut.executor.feature.pipelines.dto.PipelineStartRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface PipelineService {

    fun getPipelinesByUser(userId: Long, pageable: Pageable): Page<PipelineResponse>

    fun launchPipeline(userId: Long, pipelineStartRequest: PipelineStartRequest): PipelineResultResponse

}