package hu.bme.aut.executor.feature.jobs.dto.singlejobs

import hu.bme.aut.executor.domain.Language
import hu.bme.aut.executor.domain.SingleJobType
import hu.bme.aut.executor.domain.Upload
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class SingleJobStartRequest(
        @NotNull
        val uploadId: Long,
        @NotNull
        val language: Language?,
        @NotNull
        val job: SingleJobType
)