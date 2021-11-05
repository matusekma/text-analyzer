package hu.bme.aut.executor.feature.uploads.controller

import hu.bme.aut.executor.feature.uploads.dto.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RequestMapping("/uploads")
interface UploadApi {

    @GetMapping
    fun getUploadsByUser(@RequestHeader("userId") userId: Long,
                         pageable: Pageable): Page<UploadResponse>

    @GetMapping("/{uploadId}")
    fun getUpload(@RequestHeader("userId") userId: Long,
                  @PathVariable uploadId: Long): UploadDetailsResponse

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUpload(@RequestHeader("userId") userId: Long,
                     @RequestBody createUploadRequest: CreateUploadRequest): CreateUploadResponse

    @PutMapping("/{uploadId}")
    fun editUpload(@RequestHeader("userId") userId: Long,
                   @PathVariable uploadId: Long,
                   @RequestBody editUploadRequest: EditUploadRequest): EditUploadResponse

    @DeleteMapping("/{uploadId}")
    fun deleteUpload(@RequestHeader("userId") userId: Long,
                     @PathVariable uploadId: Long)
}