package hu.bme.aut.textanalyzerauth.feature.registration.dto

import javax.validation.constraints.NotBlank

class RegistrationRequest(
    @NotBlank
    val username: String,
    @NotBlank
    val email: String,
    @NotBlank
    val password: String,
)