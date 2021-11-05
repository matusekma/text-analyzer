package hu.bme.aut.executor.feature.jobs.dto.mappers

import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.*
import hu.bme.aut.executor.feature.nerpos.dto.NERResponse
import hu.bme.aut.executor.feature.nerpos.dto.PosTaggingResponse
import hu.bme.aut.executor.feature.sentimentanalysis.dto.SentimentAnalysisResponse

fun SentimentAnalysisResponse.toSentimentAnalysisJobResult() =
    SentimentAnalysisJobResult(results = responses.map {
        SentenceSentimentResult(
            it.sentence,
            it.sentiment,
            it.confidence
        )
    })

fun PosTaggingResponse.toPosTaggingJobResult() =
    PosTaggingJobResult(results = responses.map {
        PosTaggingResultEntity(
            text = it.text,
            lemma = it.lemma,
            tag = it.tag
        )
    })

fun NERResponse.toNERJobResult() =
    NERJobResult(results = responses.map {
        NERResultEntity(
            text = it.text,
            label = it.label
        )
    })
