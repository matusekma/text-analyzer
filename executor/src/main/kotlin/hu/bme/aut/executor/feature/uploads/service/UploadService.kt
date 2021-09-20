package hu.bme.aut.executor.feature.uploads.service

import hu.bme.aut.executor.feature.uploads.dto.CreateUploadRequest
import hu.bme.aut.executor.feature.uploads.dto.CreateUploadResponse
import hu.bme.aut.executor.feature.uploads.dto.UploadResponse

interface UploadService {

    fun getUploadsByUser(userId: Long): List<UploadResponse>

    fun createUpload(userId: Long, createUploadRequest: CreateUploadRequest): CreateUploadResponse

}