package hu.bme.aut.executor.feature.asr.service

import hu.bme.aut.executor.domain.Language
import hu.bme.aut.executor.feature.asr.dto.AsrRequest
import hu.bme.aut.executor.feature.asr.dto.AsrResponse


interface AsrService {

    fun asr(language: Language, asrRequest: AsrRequest): AsrResponse

}