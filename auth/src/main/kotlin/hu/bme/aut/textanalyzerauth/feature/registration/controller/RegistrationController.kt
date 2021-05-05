package hu.bme.aut.textanalyzerauth.feature.registration.controller

import hu.bme.aut.textanalyzerauth.feature.registration.dto.RegistrationRequest
import hu.bme.aut.textanalyzerauth.feature.registration.dto.RegistrationResponse
import hu.bme.aut.textanalyzerauth.feature.registration.service.RegistrationService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class RegistrationController(val registrationService: RegistrationService) {


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(@Validated @RequestBody registrationRequest: RegistrationRequest): RegistrationResponse {
        return registrationService.register(registrationRequest)
    }
}