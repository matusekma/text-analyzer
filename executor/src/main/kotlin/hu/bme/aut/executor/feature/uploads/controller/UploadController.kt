package hu.bme.aut.executor.feature.uploads.controller

import hu.bme.aut.executor.feature.uploads.dto.*
import hu.bme.aut.executor.feature.uploads.service.UploadService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController


@RestController
class UploadController(private val uploadService: UploadService) : UploadApi {

    override fun getUploadsByUser(userId: Long, pageable: Pageable): Page<UploadResponse> =
            uploadService.getUploadsByUser(userId, pageable)

    override fun getUpload(userId: Long, uploadId: Long): UploadDetailsResponse =
            uploadService.getUpload(userId, uploadId)

    override fun createUpload(userId: Long, createUploadRequest: CreateUploadRequest): CreateUploadResponse =
            uploadService.createUpload(userId, createUploadRequest)

    override fun editUpload(userId: Long, uploadId: Long, editUploadRequest: EditUploadRequest): EditUploadResponse =
            uploadService.editUpload(userId, uploadId, editUploadRequest)

    override fun deleteUpload(userId: Long, uploadId: Long) {
        uploadService.deleteUpload(userId, uploadId)
    }
}