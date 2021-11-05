package hu.bme.aut.executor.feature.languagedetection.dto

class LanguageDetectionResponse(
    val languages: List<String>,
    val confidences: List<Float>
)
