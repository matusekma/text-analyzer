package hu.bme.aut.textanalyzerauth.feature.login.dto

import hu.bme.aut.textanalyzerauth.domain.Role

class UserResponse(
        val id: Long,
        val username: String,
        val email: String,
        val role: Role
)
