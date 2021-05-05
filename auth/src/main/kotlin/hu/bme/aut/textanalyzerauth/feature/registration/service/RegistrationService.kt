package hu.bme.aut.textanalyzerauth.feature.registration.service

import hu.bme.aut.textanalyzerauth.feature.registration.dto.RegistrationRequest
import hu.bme.aut.textanalyzerauth.feature.registration.dto.RegistrationResponse

interface RegistrationService {
    fun register(registrationRequest: RegistrationRequest): RegistrationResponse
}
