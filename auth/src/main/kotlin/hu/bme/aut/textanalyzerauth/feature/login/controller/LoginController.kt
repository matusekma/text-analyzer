package hu.bme.aut.textanalyzerauth.feature.login.controller

import hu.bme.aut.textanalyzerauth.exception.UnauthorizedException
import hu.bme.aut.textanalyzerauth.feature.login.dto.LoginRequest
import hu.bme.aut.textanalyzerauth.feature.login.dto.LoginResponse
import hu.bme.aut.textanalyzerauth.feature.login.dto.UserResponse
import hu.bme.aut.textanalyzerauth.feature.login.service.LoginService
import org.springframework.beans.factory.annotation.Value
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
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
                        path = "/"
                        maxAge = 36_000 // 10 hours
                        secure = false // TODO for https - secure = true
                        isHttpOnly = true
                    }

    @GetMapping("/me")
    fun me(request: HttpServletRequest): UserResponse {
        val accessToken = request.cookies.firstOrNull { c -> c.name == accessTokenCookieName }
                ?: throw UnauthorizedException("No access token for /me")
        return loginService.getCurrentUser(accessToken.value)
    }

}