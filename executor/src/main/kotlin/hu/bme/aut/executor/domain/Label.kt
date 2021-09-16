package hu.bme.aut.executor.domain

import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "labels")
class Label(
        id: Long? = null,
        val name: String,
        val userId: Long,
) : BaseEntity(id)