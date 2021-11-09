package hu.bme.aut.executor.feature.asr.service

import hu.bme.aut.executor.domain.Language
import hu.bme.aut.executor.domain.toLanguageString
import hu.bme.aut.executor.exception.AsrFailedException
import hu.bme.aut.executor.feature.asr.dto.AsrRequest
import hu.bme.aut.executor.feature.asr.dto.AsrResponse
import hu.bme.aut.executor.feature.asr.dto.AsrServiceResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

@Service
class AsrServiceImpl(
    val restTemplate: RestTemplate
) : AsrService {

    @Value("\${application.services.asr.url}")
    lateinit var ASR_URL: String

    override fun asr(language: Language, asrRequest: AsrRequest): AsrResponse {
        val requestEntity = createRequestEntity(asrRequest)

        return restTemplate.postForObject(
            "$ASR_URL/asr/${language.toLanguageString()}",
            requestEntity,
            AsrServiceResponse::class.java
        )?.run {
            AsrResponse(text)
        } ?: throw AsrFailedException("ASR failed!")
    }

    private fun createRequestEntity(asrRequest: AsrRequest): HttpEntity<MultiValueMap<String, Any>> {
        val headers = HttpHeaders()
        headers.contentType = MediaType.MULTIPART_FORM_DATA

        val contentDisposition = ContentDisposition
            .builder("form-data")
            .name("file")
            .filename(asrRequest.file.originalFilename!!)
            .build()
        val fileMap: MultiValueMap<String, String> = LinkedMultiValueMap()
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
        val fileEntity: HttpEntity<ByteArray> = HttpEntity(asrRequest.file.bytes, fileMap)

        val body: MultiValueMap<String, Any> = LinkedMultiValueMap()
        body.add("file", fileEntity)

        return HttpEntity(body, headers)
    }

}