package hu.bme.aut.executor.feature.jobs.dto.pipelinejobs

import hu.bme.aut.executor.domain.JobType

class KeywordExtractionJobResult(
        val results: List<KeywordResultEntity>,
) : PipelineJobResult(type = JobType.KEYWORD_EXTRACTION)