package hu.bme.aut.executor.feature.languagedetection.service

import hu.bme.aut.executor.feature.languagedetection.dto.LanguageDetectionResponse

interface LanguageDetectionService {

    fun detectLanguage(text: String): LanguageDetectionResponse

}
