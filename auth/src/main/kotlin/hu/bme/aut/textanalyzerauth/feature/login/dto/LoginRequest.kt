package hu.bme.aut.textanalyzerauth.feature.login.dto

import javax.validation.constraints.NotBlank

class LoginRequest(
        @NotBlank
        val email: String,
        @NotBlank
        val password: String
)