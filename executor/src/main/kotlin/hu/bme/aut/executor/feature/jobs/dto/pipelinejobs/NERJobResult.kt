package hu.bme.aut.executor.feature.jobs.dto.pipelinejobs

import hu.bme.aut.executor.domain.JobType

class NERJobResult(
    val results: List<NERResultEntity>
) : PipelineJobResult(type = JobType.NER)