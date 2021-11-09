package hu.bme.aut.executor.feature.nlp.nerpos.service

import hu.bme.aut.executor.domain.Language
import hu.bme.aut.executor.feature.nlp.nerpos.dto.NERResponse
import hu.bme.aut.executor.feature.nlp.nerpos.dto.PosTaggingResponse

interface NerAndPosTaggingService {

    fun ner(language: Language, text: String): NERResponse

    fun postTag(language: Language, text: String): PosTaggingResponse
}