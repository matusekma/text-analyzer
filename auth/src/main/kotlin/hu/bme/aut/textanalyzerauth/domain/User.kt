package hu.bme.aut.textanalyzerauth.domain

import javax.persistence.*

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