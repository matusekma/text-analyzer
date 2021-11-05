package hu.bme.aut.executor.repository

import hu.bme.aut.executor.domain.Pipeline
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PipelineRepository : CrudRepository<Pipeline, Long> {

    fun findAllByUserId(userId: Long, pageable: Pageable): Page<Pipeline>

}