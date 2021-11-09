package hu.bme.aut.executor.feature.asr.controller

import hu.bme.aut.executor.domain.Language
import hu.bme.aut.executor.feature.asr.dto.AsrRequest
import hu.bme.aut.executor.feature.asr.dto.AsrResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam


@RequestMapping("/asr")
interface AsrApi {

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun asr(@RequestParam language: Language, asrRequest: AsrRequest): AsrResponse

}