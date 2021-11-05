package hu.bme.aut.executor.feature.jobs.service

import hu.bme.aut.executor.feature.jobs.dto.singlejobs.SingleJobStartRequest
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.PipelineJobRequest
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.PipelineJobResult
import hu.bme.aut.executor.feature.jobs.dto.singlejobs.SingleJobResultResponse

interface JobRunnerService {

    suspend fun runPipelineJob(pipelineJobRequest: PipelineJobRequest): PipelineJobResult

    fun runJob(userId: Long, singleJobStartRequest: SingleJobStartRequest): SingleJobResultResponse

}
