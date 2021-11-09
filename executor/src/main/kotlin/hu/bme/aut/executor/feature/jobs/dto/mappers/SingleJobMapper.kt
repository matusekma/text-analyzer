package hu.bme.aut.executor.feature.jobs.dto.mappers

import hu.bme.aut.executor.feature.jobs.dto.singlejobs.LanguageDetectionJobResult
import hu.bme.aut.executor.feature.jobs.dto.singlejobs.PunctuationRestorationJobResult
import hu.bme.aut.executor.feature.nlp.languagedetection.dto.LanguageDetectionResponse
import hu.bme.aut.executor.feature.nlp.punctuationrestoration.dto.PunctuationRestorationResponse

fun LanguageDetectionResponse.toLanguageDetectionJobResult() =
    LanguageDetectionJobResult(languages, confidences)

fun PunctuationRestorationResponse.toPunctuationRestorationJobResult() =
    PunctuationRestorationJobResult(restoredText)