package hu.bme.aut.executor.feature.jobs.dto.pipelinejobs

import hu.bme.aut.executor.domain.JobType

class KeywordExtractionResult(
        val keywords: List<String>
) : PipelineJobResult(type = JobType.KEYWORD_EXTRACTION)