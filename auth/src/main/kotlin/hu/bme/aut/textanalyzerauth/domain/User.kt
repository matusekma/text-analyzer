package hu.bme.aut.textanalyzerauth.domain

import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
        id: Long? = null,
        val username: String,
        val email: String,
        val password: String,
        @Enumerated(EnumType.STRING)
        val role: Role
) : BaseEntity(id)