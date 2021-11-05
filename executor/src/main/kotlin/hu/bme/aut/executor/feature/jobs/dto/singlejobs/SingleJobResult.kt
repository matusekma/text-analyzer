package hu.bme.aut.executor.feature.jobs.dto.singlejobs

import hu.bme.aut.executor.domain.SingleJobType

sealed class SingleJobResult(val isFailed: Boolean = false, val type: SingleJobType)