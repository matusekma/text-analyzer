package hu.bme.aut.executor.feature.uploads.dto

import javax.validation.constraints.NotBlank

class EditUploadRequest(
        @NotBlank
        val name: String,
        @NotBlank
        val description: String,
        @NotBlank
        val text: String,
        val labelIds: List<Long>
)