package hu.bme.aut.executor.feature.pipelines.service

import hu.bme.aut.executor.domain.Pipeline
import hu.bme.aut.executor.domain.Upload
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.FailedPipelineJobResult
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.PipelineJobRequest
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.PipelineJobResult
import hu.bme.aut.executor.feature.jobs.service.JobRunnerService
import hu.bme.aut.executor.feature.pipelines.dto.*
import hu.bme.aut.executor.repository.PipelineRepository
import hu.bme.aut.executor.repository.UploadRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class PipelineServiceImpl(
    private val pipelineRepository: PipelineRepository,
    private val uploadRepository: UploadRepository,
    private val jobRunnerService: JobRunnerService
) : PipelineService {

    @Transactional
    override fun getPipelinesByUser(userId: Long, pageable: Pageable): Page<PipelineResponse> =
        pipelineRepository.findAllByUserId(userId, pageable)
            .map(Pipeline::toPipelineResponse)

    @Transactional
    override fun launchPipeline(userId: Long, pipelineStartRequest: PipelineStartRequest): PipelineResultResponse {
        val upload = uploadRepository.findByIdAndUserId(pipelineStartRequest.uploadId, userId)
            .orElseThrow { throw EntityNotFoundException("Upload not found!") }

        val pipelineResult = runBlocking {
            executeJobs(pipelineStartRequest, upload)
        }

        var pipeline = pipelineStartRequest.toPipeline(userId, upload)
        pipeline = pipelineRepository.save(pipeline)

        return PipelineResultResponse(
            id = pipeline.id!!,
            uploadId = upload.id!!,
            results = pipelineResult
        )
    }

    private suspend fun executeJobs(
        pipelineStartRequest: PipelineStartRequest,
        upload: Upload
    ): List<PipelineJobResult> {
        val jobs = pipelineStartRequest.jobs
        return supervisorScope {  // this: CoroutineScope
            jobs.map {
                val pipelineJobRequest = PipelineJobRequest(
                    upload = upload,
                    language = pipelineStartRequest.language,
                    job = it
                )
                async { jobRunnerService.runPipelineJob(pipelineJobRequest) }
            }.mapIndexed { i, deferredResult ->
                try {
                    deferredResult.await()
                } catch (e: RuntimeException) {
                    println(e.message)
                    FailedPipelineJobResult(jobs[i], e.message!!)
                }
            }
        }
    }
}