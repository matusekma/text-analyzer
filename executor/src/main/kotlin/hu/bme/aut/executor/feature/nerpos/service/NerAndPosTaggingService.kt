package hu.bme.aut.executor.feature.nerpos.service

import hu.bme.aut.executor.domain.Language
import hu.bme.aut.executor.feature.nerpos.dto.NERResponse
import hu.bme.aut.executor.feature.nerpos.dto.PosTaggingResponse

interface NerAndPosTaggingService {

    fun ner(language: Language, text: String): NERResponse

    fun postTag(language: Language, text: String): PosTaggingResponse
}