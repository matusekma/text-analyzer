package hu.bme.aut.executor.feature.uploads.dto

import java.time.OffsetDateTime

class UploadResponse(
    val id: Long,
    val name: String,
    val description: String,
    val userId: Long,
    val createdAt: OffsetDateTime
)
