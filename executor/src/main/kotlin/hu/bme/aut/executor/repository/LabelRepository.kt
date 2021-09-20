package hu.bme.aut.executor.repository

import hu.bme.aut.executor.domain.Label
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LabelRepository : CrudRepository<Label, Long> {

    fun findAllByIdIn(ids: List<Long>): List<Label>

}