package hu.bme.aut.executor.feature.uploads.controller

import hu.bme.aut.executor.feature.uploads.dto.CreateUploadRequest
import hu.bme.aut.executor.feature.uploads.dto.CreateUploadResponse
import hu.bme.aut.executor.feature.uploads.dto.UploadResponse
import hu.bme.aut.executor.feature.uploads.service.UploadService
import org.springframework.web.bind.annotation.RestController

@RestController
class UploadController(private val uploadService: UploadService) : UploadApi {

    override fun getUploadsByUser(): List<UploadResponse> {
        val userId = 1L // TODO get user from request
        return uploadService.getUploadsByUser(userId)
    }

    override fun createUpload(createUploadRequest: CreateUploadRequest): CreateUploadResponse {
        val userId = 1L // TODO get user from request
        return uploadService.createUpload(userId, createUploadRequest)
    }

}