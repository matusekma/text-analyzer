package hu.bme.aut.textanalyzerauth.domain

import java.time.OffsetDateTime
import javax.persistence.*

@MappedSuperclass
open class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null
) {

    var createdAt: OffsetDateTime? = null

    var lastModifiedAt: OffsetDateTime? = null

    @PrePersist
    fun onPrePersist() {
        val now = OffsetDateTime.now()
        createdAt = now
        lastModifiedAt = now
    }

    @PreUpdate
    fun onPreUpdate() {
        lastModifiedAt = OffsetDateTime.now()
    }
}