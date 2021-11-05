package hu.bme.aut.textanalyzerauth.feature.login.service

import hu.bme.aut.textanalyzerauth.feature.login.dto.LoginRequest
import hu.bme.aut.textanalyzerauth.feature.login.dto.LoginResponse
import hu.bme.aut.textanalyzerauth.feature.login.dto.UserResponse

interface LoginService {

    fun login(loginRequest: LoginRequest) : LoginResponse

    fun getCurrentUser(accessToken: String): UserResponse
}