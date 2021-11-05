package hu.bme.aut.executor.feature.jobs.dto.pipelinejobs

import hu.bme.aut.executor.domain.JobType

class PosTaggingJobResult(
        val results: List<PosTaggingResultEntity>
) : PipelineJobResult(type = JobType.POS_TAG)