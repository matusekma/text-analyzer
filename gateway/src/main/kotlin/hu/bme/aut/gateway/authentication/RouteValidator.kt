package hu.bme.aut.gateway.authentication

import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component


@Component
class RouterValidator {

    val openApiEndpoints = listOf("/register", "/login", "/logout")

    fun isSecured(request: ServerHttpRequest): Boolean {
        return openApiEndpoints
                .stream()
                .noneMatch { uri -> request.uri.path.contains(uri) }
    }
}
