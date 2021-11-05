package hu.bme.aut.executor.feature.uploads.dto

import javax.validation.constraints.NotBlank

class CreateUploadRequest(
        @NotBlank
        val name: String,
        val description: String,
        @NotBlank
        val text: String,
        val userId: Long,
        val labelIds: List<Long>
)