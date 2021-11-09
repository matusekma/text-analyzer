package hu.bme.aut.executor.feature.jobs.dto.singlejobs

import hu.bme.aut.executor.domain.SingleJobType

class PunctuationRestorationJobResult(
    val restoredText: String
) : SingleJobResult(type = SingleJobType.PUNCTUATION_RESTORATION)