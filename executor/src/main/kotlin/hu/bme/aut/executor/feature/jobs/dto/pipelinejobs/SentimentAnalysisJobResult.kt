package hu.bme.aut.executor.feature.jobs.dto.pipelinejobs

import hu.bme.aut.executor.domain.JobType

class SentimentAnalysisJobResult(
    val results: List<SentenceSentimentResult>,
    ) : PipelineJobResult(type = JobType.SENTIMENT_ANALYSIS)