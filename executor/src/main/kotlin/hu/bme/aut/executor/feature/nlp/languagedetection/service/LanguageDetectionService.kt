package hu.bme.aut.executor.feature.nlp.languagedetection.service

import hu.bme.aut.executor.feature.nlp.languagedetection.dto.LanguageDetectionResponse

interface LanguageDetectionService {

    fun detectLanguage(text: String): LanguageDetectionResponse

}
