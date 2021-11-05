package hu.bme.aut.executor.feature.uploads.service

import hu.bme.aut.executor.feature.uploads.dto.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface UploadService {

    fun getUploadsByUser(userId: Long, pageable: Pageable): Page<UploadResponse>

    fun getUpload(userId: Long, uploadId: Long): UploadDetailsResponse

    fun createUpload(userId: Long, createUploadRequest: CreateUploadRequest): CreateUploadResponse

    fun deleteUpload(userId: Long, uploadId: Long)

    fun editUpload(userId: Long, uploadId: Long, editUploadRequest: EditUploadRequest): EditUploadResponse
}