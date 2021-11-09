package hu.bme.aut.executor.feature.jobs.service

import hu.bme.aut.executor.domain.JobType
import hu.bme.aut.executor.domain.SingleJobType
import hu.bme.aut.executor.feature.jobs.dto.mappers.*
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.KeywordExtractionJobRequest
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.PipelineJobRequest
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.PipelineJobResult
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.SummarizerJobRequest
import hu.bme.aut.executor.feature.jobs.dto.singlejobs.ProfanityFilterResult
import hu.bme.aut.executor.feature.jobs.dto.singlejobs.PunctuationRestorationJobResult
import hu.bme.aut.executor.feature.jobs.dto.singlejobs.SingleJobResultResponse
import hu.bme.aut.executor.feature.jobs.dto.singlejobs.SingleJobStartRequest
import hu.bme.aut.executor.feature.nlp.keywordextraction.service.KeywordExtractionService
import hu.bme.aut.executor.feature.nlp.languagedetection.service.LanguageDetectionService
import hu.bme.aut.executor.feature.nlp.nerpos.service.NerAndPosTaggingService
import hu.bme.aut.executor.feature.nlp.profanityfilter.service.ProfanityFilterService
import hu.bme.aut.executor.feature.nlp.punctuationrestoration.dto.PunctuationRestorationRequest
import hu.bme.aut.executor.feature.nlp.punctuationrestoration.service.PunctuationRestorationService
import hu.bme.aut.executor.feature.nlp.sentimentanalysis.service.SentimentAnalysisService
import hu.bme.aut.executor.feature.nlp.summarizer.service.SummarizerService
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
    private val nerAndPosTaggingService: NerAndPosTaggingService,
    private val summarizerService: SummarizerService,
    private val keywordExtractionService: KeywordExtractionService,
    private val punctuationRestorationService: PunctuationRestorationService
) : JobRunnerService {

    override suspend fun runPipelineJob(pipelineJobRequest: PipelineJobRequest): PipelineJobResult {
        val language = pipelineJobRequest.language
        val text = pipelineJobRequest.upload.text
        val options = pipelineJobRequest.options
        return when (pipelineJobRequest.job) {
            JobType.SENTIMENT_ANALYSIS ->
                sentimentAnalysisService.analyze(language, text).toSentimentAnalysisJobResult()
            JobType.POS_TAG ->
                nerAndPosTaggingService.postTag(language, text).toPosTaggingJobResult()
            JobType.KEYWORD_EXTRACTION ->
                keywordExtractionService.extract(KeywordExtractionJobRequest(language, text))
                    .toKeywordExtractionJobResult()
            JobType.NER ->
                nerAndPosTaggingService.ner(language, text).toNERJobResult()
            JobType.SUMMARIZATION -> {
                val length: Int = try {
                    Integer.parseInt(options?.get("length"))
                } catch (e: NumberFormatException) {
                    3
                }
                summarizerService.summarize(SummarizerJobRequest(language, text, length)).toSummarizerJobResult()
            }
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
            SingleJobType.PUNCTUATION_RESTORATION -> punctuationRestorationService.punctuate(
                PunctuationRestorationRequest(upload.text)
            ).toPunctuationRestorationJobResult()
        }
        return SingleJobResultResponse(uploadId = upload.id!!, result)
    }

}

