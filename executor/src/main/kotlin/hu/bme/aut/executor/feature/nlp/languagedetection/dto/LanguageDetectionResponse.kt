package hu.bme.aut.executor.feature.nlp.languagedetection.dto

class LanguageDetectionResponse(
    val languages: List<String>,
    val confidences: List<Float>
)
