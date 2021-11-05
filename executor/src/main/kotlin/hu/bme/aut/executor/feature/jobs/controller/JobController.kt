package hu.bme.aut.executor.feature.jobs.controller

import hu.bme.aut.executor.feature.jobs.dto.singlejobs.SingleJobStartRequest
import hu.bme.aut.executor.feature.jobs.dto.singlejobs.SingleJobResultResponse
import hu.bme.aut.executor.feature.jobs.service.JobRunnerService
import org.springframework.web.bind.annotation.RestController

@RestController
class JobController(private val jobRunnerService: JobRunnerService) : JobApi {

    override fun runJob(userId: Long, singleJobStartRequest: SingleJobStartRequest): SingleJobResultResponse =
        jobRunnerService.runJob(userId, singleJobStartRequest)

}