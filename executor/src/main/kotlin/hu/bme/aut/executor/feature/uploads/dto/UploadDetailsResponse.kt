package hu.bme.aut.executor.feature.uploads.dto

import java.time.OffsetDateTime

class UploadDetailsResponse(
        val id: Long,
        val name: String,
        val description: String,
        val text: String,
        val userId: Long,
        val createdAt: OffsetDateTime,
        val labelIds: List<Long>,
)
