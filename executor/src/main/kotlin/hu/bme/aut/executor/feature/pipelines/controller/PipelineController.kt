package hu.bme.aut.executor.feature.pipelines.controller

import hu.bme.aut.executor.feature.pipelines.dto.PipelineResponse
import hu.bme.aut.executor.feature.pipelines.dto.PipelineResultResponse
import hu.bme.aut.executor.feature.pipelines.dto.PipelineStartRequest
import hu.bme.aut.executor.feature.pipelines.service.PipelineService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController

@RestController
class PipelineController(private val pipelineService: PipelineService): PipelineApi {

    override fun getPipelinesByUser(userId: Long, pageable: Pageable): Page<PipelineResponse> =
     pipelineService.getPipelinesByUser(userId, pageable)

    override fun launchPipeline(userId: Long, pipelineStartRequest: PipelineStartRequest): PipelineResultResponse {
        return pipelineService.launchPipeline(userId, pipelineStartRequest)
    }

}