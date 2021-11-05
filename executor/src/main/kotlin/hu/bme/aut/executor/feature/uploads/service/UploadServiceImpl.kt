package hu.bme.aut.executor.feature.uploads.service

import hu.bme.aut.executor.domain.Label
import hu.bme.aut.executor.domain.Upload
import hu.bme.aut.executor.exception.UnauthorizedException
import hu.bme.aut.executor.feature.uploads.dto.*
import hu.bme.aut.executor.repository.LabelRepository
import hu.bme.aut.executor.repository.UploadRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class UploadServiceImpl(
        private val uploadRepository: UploadRepository,
        private val labelRepository: LabelRepository)
    : UploadService {

    @Transactional
    override fun getUploadsByUser(userId: Long, pageable: Pageable): Page<UploadResponse> =
            uploadRepository.findAllByUserId(userId, pageable)
                    .map(Upload::toUploadResponse)

    override fun getUpload(userId: Long, uploadId: Long): UploadDetailsResponse {
        val upload = uploadRepository.findById(uploadId)
                .orElseThrow { throw EntityNotFoundException("Upload not found!") }
        if (upload.userId != userId) {
            throw UnauthorizedException("Unauthorized to get upload!")
        }
        return upload.toUploadDetailsResponse()
    }

    @Transactional
    override fun createUpload(userId: Long, createUploadRequest: CreateUploadRequest): CreateUploadResponse {
        val upload = createUploadRequest.toUpload(userId)
        val labels = labelRepository.findAllByIdIn(createUploadRequest.labelIds)
        upload.addLabels(labels)
        return uploadRepository.save(upload)
                .let(Upload::toCreateUploadResponse)
    }

    @Transactional
    override fun deleteUpload(userId: Long, uploadId: Long) {
        val upload = uploadRepository.getById(uploadId) ?: return
        if (upload.userId == userId) {
            upload.removeAllPipelines()
            uploadRepository.delete(upload)
        } else {
            throw UnauthorizedException("Unauthorized to delete upload!")
        }
    }

    @Transactional
    override fun editUpload(userId: Long, uploadId: Long, editUploadRequest: EditUploadRequest): EditUploadResponse {
        val upload = uploadRepository.findById(uploadId)
                .orElseThrow { throw EntityNotFoundException("Upload not found!") }
        if (upload.userId != userId) {
            throw UnauthorizedException("Unauthorized to edit upload!")
        }

        val labels = labelRepository.findAllByIdIn(editUploadRequest.labelIds)

        upload.apply {
            name = editUploadRequest.name
            description = editUploadRequest.description
            text = editUploadRequest.text
        }

        upload.removeAllLabels()
        upload.addLabels(labels)

        return upload.toEditUploadResponse()
    }


}