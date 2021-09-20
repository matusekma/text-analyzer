package hu.bme.aut.executor.domain

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        open var id: Long? = null
)