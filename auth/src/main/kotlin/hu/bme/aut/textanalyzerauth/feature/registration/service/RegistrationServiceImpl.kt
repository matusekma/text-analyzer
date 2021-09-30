package hu.bme.aut.textanalyzerauth.feature.registration.service

import hu.bme.aut.textanalyzerauth.domain.Role
import hu.bme.aut.textanalyzerauth.domain.User
import hu.bme.aut.textanalyzerauth.exception.UserAlreadyExistsException
import hu.bme.aut.textanalyzerauth.feature.registration.dto.RegistrationRequest
import hu.bme.aut.textanalyzerauth.feature.registration.dto.RegistrationResponse
import hu.bme.aut.textanalyzerauth.feature.registration.dto.toRegistrationResponse
import hu.bme.aut.textanalyzerauth.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class RegistrationServiceImpl(
        val passwordEncoder: PasswordEncoder,
        val userRepository: UserRepository
) : RegistrationService {

    override fun register(registrationRequest: RegistrationRequest): RegistrationResponse {
        checkEmailExists(registrationRequest.email)
        checkUsernameExists(registrationRequest.username)

        val user = User(
                username = registrationRequest.username,
                email = registrationRequest.email,
                password = passwordEncoder.encode(registrationRequest.password),
                role = Role.USER
        )

        return userRepository.save(user).toRegistrationResponse()
    }

    private fun checkEmailExists(email: String) {
        userRepository.findByEmail(email)?.let { throw UserAlreadyExistsException(email) }
    }

    private fun checkUsernameExists(username: String) {
        userRepository.findByUsername(username)?.let { throw UserAlreadyExistsException(username) }
    }
}
