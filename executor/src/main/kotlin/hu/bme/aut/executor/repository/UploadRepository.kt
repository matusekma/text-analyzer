package hu.bme.aut.executor.repository

import hu.bme.aut.executor.domain.Upload
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UploadRepository : CrudRepository<Upload, Long> {

    fun findAllByUserId(userId: Long): List<Upload>

}