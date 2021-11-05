package hu.bme.aut.executor.feature.uploads.dto

class EditUploadResponse(
        val id: Long,
        val name: String,
        val description: String,
        val userId: Long,
        val text: String,
        val labelIds: List<Long>,
)