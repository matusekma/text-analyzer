package hu.bme.aut.textanalyzerauth.auth.dto

data class LoginRequest(
        val email: String,
        val password: String)