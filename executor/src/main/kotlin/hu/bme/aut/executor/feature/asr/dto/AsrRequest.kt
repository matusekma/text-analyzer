package hu.bme.aut.executor.feature.asr.dto

import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class AsrRequest(
    @NotNull
    val file: MultipartFile,
)
