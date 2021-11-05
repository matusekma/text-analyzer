package hu.bme.aut.executor.feature.jobs.controller

import hu.bme.aut.executor.feature.jobs.dto.singlejobs.SingleJobStartRequest
import hu.bme.aut.executor.feature.jobs.dto.singlejobs.SingleJobResultResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/jobs")
interface JobApi {

    @PostMapping
    fun runJob(
        @RequestHeader("userId") userId: Long,
        @Validated @RequestBody singleJobStartRequest: SingleJobStartRequest
    ): SingleJobResultResponse
}