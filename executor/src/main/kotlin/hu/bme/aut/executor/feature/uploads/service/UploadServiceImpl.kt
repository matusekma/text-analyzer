package hu.bme.aut.executor.feature.uploads.service

import hu.bme.aut.executor.domain.Upload
import hu.bme.aut.executor.feature.uploads.dto.*
import hu.bme.aut.executor.repository.LabelRepository
import hu.bme.aut.executor.repository.UploadRepository
import org.springframework.stereotype.Service

@Service
class UploadServiceImpl(
        private val uploadRepository: UploadRepository,
        private val labelRepository: LabelRepository)
    : UploadService {

    override fun getUploadsByUser(userId: Long): List<UploadResponse> =
            uploadRepository.findAllByUserId(userId)
                    .map(Upload::toUploadResponse)

    override fun createUpload(userId: Long, createUploadRequest: CreateUploadRequest): CreateUploadResponse {
        val upload = createUploadRequest.toUpload(userId)
        val labels = labelRepository.findAllByIdIn(createUploadRequest.labelIds)
        upload.addLabels(labels)
        return uploadRepository.save(upload)
                .let(Upload::toCreateUploadResponse)
    }

}