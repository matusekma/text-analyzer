package hu.bme.aut.textanalyzerauth.feature.login.dto

class LoginResponse(
        val token: String,
        val user: UserResponse
)