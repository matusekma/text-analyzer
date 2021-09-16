package hu.bme.aut.executor.repository

import hu.bme.aut.executor.domain.Label
import org.springframework.data.repository.CrudRepository

interface LabelRepository : CrudRepository<Label, Long>