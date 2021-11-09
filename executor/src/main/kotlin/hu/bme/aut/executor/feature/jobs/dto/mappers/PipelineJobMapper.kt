package hu.bme.aut.executor.feature.jobs.dto.mappers

import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.SummarizerJobResult
import hu.bme.aut.executor.feature.jobs.dto.pipelinejobs.*
import hu.bme.aut.executor.feature.nlp.keywordextraction.dto.KeywordExtractionResponse
import hu.bme.aut.executor.feature.nlp.nerpos.dto.NERResponse
import hu.bme.aut.executor.feature.nlp.nerpos.dto.PosTaggingResponse
import hu.bme.aut.executor.feature.nlp.sentimentanalysis.dto.SentimentAnalysisResponse
import hu.bme.aut.executor.feature.nlp.summarizer.dto.SummarizerResponse

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

fun SummarizerResponse.toSummarizerJobResult() =
    SummarizerJobResult(results = results)

fun KeywordExtractionResponse.toKeywordExtractionJobResult() =
    KeywordExtractionJobResult(results = results.map {
        KeywordResultEntity(
            keyword = it.keyword,
            score = it.score
        )
    })
