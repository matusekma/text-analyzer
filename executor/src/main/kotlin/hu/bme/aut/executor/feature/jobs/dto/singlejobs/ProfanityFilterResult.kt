package hu.bme.aut.executor.feature.jobs.dto.singlejobs

import hu.bme.aut.executor.domain.SingleJobType

class ProfanityFilterResult(
    val filteredText: String
) : SingleJobResult(type = SingleJobType.PROFANITY_FILTER)