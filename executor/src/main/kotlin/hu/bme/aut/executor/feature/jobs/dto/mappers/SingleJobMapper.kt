package hu.bme.aut.executor.feature.jobs.dto.mappers

import hu.bme.aut.executor.feature.jobs.dto.singlejobs.LanguageDetectionJobResult
import hu.bme.aut.executor.feature.languagedetection.dto.LanguageDetectionResponse

fun LanguageDetectionResponse.toLanguageDetectionJobResult() =
    LanguageDetectionJobResult(languages, confidences)
