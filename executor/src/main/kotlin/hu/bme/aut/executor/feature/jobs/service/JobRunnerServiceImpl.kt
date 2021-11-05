package hu.bme.aut.executor.feature.jobs.service

import hu.bme.aut.executor.domain.JobType
import hu.bme.aut.executor.domain.SingleJobType
import hu.bme.aut.executor.feature.jobs.dto.mappers.toLanguageDetectionJobResult
import hu.bme.aut.executor.feature.jobs.dto.mappers.toNERJobResult
import hu.bme.aut.executor.feature.jobs.dto.mappers.toPosTaggingJobResult
import hu.bme.aut.executor.feature.jobs.dto.mappers.toSentimentAnalysisJobResult
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.KeywordExtractionResult
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.PipelineJobRequest
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.PipelineJobResult
import hu.bme.aut.executor.feature.jobs.dto.singlejobs.ProfanityFilterResult
import hu.bme.aut.executor.feature.jobs.dto.singlejobs.PunctuationRestorationResult
import hu.bme.aut.executor.feature.jobs.dto.singlejobs.SingleJobResultResponse
import hu.bme.aut.executor.feature.jobs.dto.singlejobs.SingleJobStartRequest
import hu.bme.aut.executor.feature.languagedetection.service.LanguageDetectionService
import hu.bme.aut.executor.feature.nerpos.service.NerAndPosTaggingService
import hu.bme.aut.executor.feature.profanityfilter.service.ProfanityFilterService
import hu.bme.aut.executor.feature.sentimentanalysis.service.SentimentAnalysisService
import hu.bme.aut.executor.repository.UploadRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class JobRunnerServiceImpl(
    private val uploadRepository: UploadRepository,
    private val profanityFilterService: ProfanityFilterService,
    private val languageDetectionService: LanguageDetectionService,
    private val sentimentAnalysisService: SentimentAnalysisService,
    private val nerAndPosTaggingService: NerAndPosTaggingService
) : JobRunnerService {

    override suspend fun runPipelineJob(pipelineJobRequest: PipelineJobRequest): PipelineJobResult {
        val language = pipelineJobRequest.language
        val text = pipelineJobRequest.upload.text
        return when (pipelineJobRequest.job) {
            JobType.SENTIMENT_ANALYSIS ->
                sentimentAnalysisService.analyze(language, text).toSentimentAnalysisJobResult()
            JobType.POS_TAG ->
                nerAndPosTaggingService.postTag(language, text).toPosTaggingJobResult()
            JobType.KEYWORD_EXTRACTION ->
                KeywordExtractionResult(listOf("keyword1", "keyword2"))
            JobType.NER ->
                nerAndPosTaggingService.ner(language, text).toNERJobResult()
        }
    }

    @Transactional
    override fun runJob(userId: Long, singleJobStartRequest: SingleJobStartRequest): SingleJobResultResponse {
        val upload = uploadRepository.findByIdAndUserId(singleJobStartRequest.uploadId, userId)
            .orElseThrow { throw EntityNotFoundException("Upload not found!") }

        val result = when (singleJobStartRequest.job) {
            SingleJobType.LANGUAGE_DETECTION ->
                languageDetectionService.detectLanguage(upload.text).toLanguageDetectionJobResult()
            SingleJobType.PROFANITY_FILTER -> profanityFilterService.filterText(upload.text).run {
                ProfanityFilterResult(this)
            }
            SingleJobType.PUNCTUATION_RESTORATION -> PunctuationRestorationResult("punctuation restoration result")
        }
        return SingleJobResultResponse(uploadId = upload.id!!, result)
    }

}

