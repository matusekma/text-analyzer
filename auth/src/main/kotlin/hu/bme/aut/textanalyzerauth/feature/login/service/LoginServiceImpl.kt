package hu.bme.aut.textanalyzerauth.feature.login.service

import hu.bme.aut.textanalyzerauth.auth.jwt.JwtUtilService
import hu.bme.aut.textanalyzerauth.auth.jwt.UserTokenData
import hu.bme.aut.textanalyzerauth.exception.UnauthorizedException
import hu.bme.aut.textanalyzerauth.feature.login.dto.LoginRequest
import hu.bme.aut.textanalyzerauth.feature.login.dto.LoginResponse
import hu.bme.aut.textanalyzerauth.feature.login.dto.UserResponse
import hu.bme.aut.textanalyzerauth.feature.login.dto.toUserResponse
import hu.bme.aut.textanalyzerauth.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class LoginServiceImpl(
        val authenticationManager: AuthenticationManager,
        val jwtUtilService: JwtUtilService,
        val userRepository: UserRepository
) : LoginService {

    override fun login(loginRequest: LoginRequest): LoginResponse {
        val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password)
        )

        val principal = authentication.principal as User
        val user = userRepository.findByEmail(principal.username) ?: throw UnauthorizedException("User not found")
        val token: String = jwtUtilService.generateToken(UserTokenData(user.id!!, user.email))

        return LoginResponse(token, user.toUserResponse())
    }

    override fun getCurrentUser(accessToken: String): UserResponse {
        if (jwtUtilService.isTokenExpired(accessToken)) {
            throw UnauthorizedException("Token is expired!")
        }
        val userId = jwtUtilService.extractClaim(accessToken) { c -> (c["userId"] as Number?)?.toLong() }
                ?: throw UnauthorizedException("Token is expired!")
        return userRepository.findById(userId)
                .orElseThrow { EntityNotFoundException("User not found") }
                .toUserResponse()
    }

}