package hu.bme.aut.executor.feature.pipelines.controller

import hu.bme.aut.executor.feature.pipelines.dto.PipelineResponse
import hu.bme.aut.executor.feature.pipelines.dto.PipelineResultResponse
import hu.bme.aut.executor.feature.pipelines.dto.PipelineStartRequest
import hu.bme.aut.executor.feature.uploads.dto.UploadResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RequestMapping("/pipelines")
interface PipelineApi {

    @GetMapping
    fun getPipelinesByUser(@RequestHeader("userId") userId: Long,
                         pageable: Pageable
    ): Page<PipelineResponse>

    @PostMapping
    fun launchPipeline(@RequestHeader("userId") userId: Long,
                       @Validated @RequestBody pipelineStartRequest: PipelineStartRequest): PipelineResultResponse
}