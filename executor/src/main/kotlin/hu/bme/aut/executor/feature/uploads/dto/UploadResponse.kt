package hu.bme.aut.executor.feature.uploads.dto

class UploadResponse(
        val id: Long,
        val name: String,
        val description: String,
        val text: String,
        val userId: Long,
        val labelIds: List<Long>,
)
