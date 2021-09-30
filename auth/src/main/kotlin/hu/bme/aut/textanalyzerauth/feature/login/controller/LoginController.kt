package hu.bme.aut.textanalyzerauth.feature.login.controller

import hu.bme.aut.textanalyzerauth.feature.login.dto.LoginRequest
import hu.bme.aut.textanalyzerauth.feature.login.dto.LoginResponse
import hu.bme.aut.textanalyzerauth.feature.login.service.LoginService
import org.springframework.beans.factory.annotation.Value
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
class LoginController(
        @Value("\${jwt.accessTokenCookieName}")
        private val accessTokenCookieName: String,
        private val loginService: LoginService
) {

    @PostMapping("/login")
    fun login(@Validated @RequestBody loginRequest: LoginRequest, response: HttpServletResponse): LoginResponse {
        val loginResponse = loginService.login(loginRequest)
        val cookie = getAccessTokenCookie(loginResponse.token)
        response.addCookie(cookie)
        return loginResponse
    }

    private fun getAccessTokenCookie(token: String) =
            Cookie(accessTokenCookieName, token)
                    .apply {
                        // TODO uncomment for https - secure = true
                        isHttpOnly = true
                    }


}