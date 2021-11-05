package hu.bme.aut.executor.feature.jobs.dto.pipelinejobs

import hu.bme.aut.executor.domain.JobType

sealed class PipelineJobResult(val isFailed: Boolean = false, val type: JobType)