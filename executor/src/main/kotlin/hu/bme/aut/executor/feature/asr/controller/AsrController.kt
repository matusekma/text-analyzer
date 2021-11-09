package hu.bme.aut.executor.feature.asr.controller

import hu.bme.aut.executor.domain.Language
import hu.bme.aut.executor.feature.asr.dto.AsrRequest
import hu.bme.aut.executor.feature.asr.dto.AsrResponse
import hu.bme.aut.executor.feature.asr.service.AsrService
import org.springframework.web.bind.annotation.RestController

@RestController
class AsrController(private val asrService: AsrService) : AsrApi {

    override fun asr(language: Language, asrRequest: AsrRequest): AsrResponse =
        asrService.asr(language, asrRequest)

}