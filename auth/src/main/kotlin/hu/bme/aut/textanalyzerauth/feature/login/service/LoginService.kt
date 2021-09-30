package hu.bme.aut.textanalyzerauth.feature.login.service

import hu.bme.aut.textanalyzerauth.feature.login.dto.LoginRequest
import hu.bme.aut.textanalyzerauth.feature.login.dto.LoginResponse

interface LoginService {

    fun login(loginRequest: LoginRequest) : LoginResponse

}