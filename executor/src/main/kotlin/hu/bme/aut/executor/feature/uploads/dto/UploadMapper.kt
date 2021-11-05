package hu.bme.aut.executor.feature.uploads.dto

import hu.bme.aut.executor.domain.Upload

fun CreateUploadRequest.toUpload(userId: Long) =
        Upload(
                name = name,
                description = description,
                text = text,
                userId = userId
        )

fun Upload.toCreateUploadResponse() =
        CreateUploadResponse(
                id = id!!,
                name = name,
                description = description,
                text = text,
                userId = userId,
                labelIds = labels.map { it.id!! }
        )

fun Upload.toUploadResponse() =
        UploadResponse(
                id = id!!,
                name = name,
                description = description,
                userId = userId,
                createdAt = createdAt!!
        )

fun Upload.toUploadDetailsResponse() =
        UploadDetailsResponse(
                id = id!!,
                name = name,
                description = description,
                userId = userId,
                text = text,
                createdAt = createdAt!!,
                labelIds = labels.map { it.id!! }
        )

fun Upload.toEditUploadResponse() =
        EditUploadResponse(
                id = id!!,
                name = name,
                description = description,
                userId = userId,
                text = text,
                labelIds = labels.map { it.id!! }
        )
