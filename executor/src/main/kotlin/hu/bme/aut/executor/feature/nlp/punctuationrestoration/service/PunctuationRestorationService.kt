package hu.bme.aut.executor.feature.nlp.punctuationrestoration.service

import hu.bme.aut.executor.feature.nlp.punctuationrestoration.dto.PunctuationRestorationRequest
import hu.bme.aut.executor.feature.nlp.punctuationrestoration.dto.PunctuationRestorationResponse

interface PunctuationRestorationService {

    fun punctuate(punctuationRestorationRequest: PunctuationRestorationRequest): PunctuationRestorationResponse
}