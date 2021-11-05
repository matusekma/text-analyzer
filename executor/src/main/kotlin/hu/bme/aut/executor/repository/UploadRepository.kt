package hu.bme.aut.executor.repository

import hu.bme.aut.executor.domain.Upload
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UploadRepository : CrudRepository<Upload, Long> {

    fun findAllByUserId(userId: Long, pageable: Pageable): Page<Upload>

    fun getById(uploadId: Long): Upload?

    fun findByIdAndUserId(id: Long, userId: Long): Optional<Upload>

}