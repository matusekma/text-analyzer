package hu.bme.aut.gateway.authentication

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@Component
class AuthenticationFilter(
        @Value("\${jwt.accessTokenCookieName}")
        private val accessTokenCookieName: String,
        private val routerValidator: RouterValidator,
        private val jwtUtil: JwtUtil
) : GatewayFilter {

    override fun filter(exchange: ServerWebExchange, filterChain: GatewayFilterChain): Mono<Void>? {
        val request: ServerHttpRequest = exchange.request
        if (routerValidator.isSecured(request)) {
            val accessToken = getAccessTokenFromCookie(request)

            if (accessToken == null || jwtUtil.isInvalid(accessToken)) {
                return onError(exchange, "Access token is missing or invalid", HttpStatus.UNAUTHORIZED)
            }

            populateRequestWithHeaders(exchange, accessToken)
        }
        return filterChain.filter(exchange)
    }

    private fun onError(exchange: ServerWebExchange, err: String, httpStatus: HttpStatus): Mono<Void> {
        //TODO use err string
        val response: ServerHttpResponse = exchange.response
        response.statusCode = httpStatus
        return response.setComplete()
    }

    private fun getAccessTokenFromCookie(request: ServerHttpRequest): String? =
            request.cookies.getFirst(accessTokenCookieName)?.value

    private fun populateRequestWithHeaders(exchange: ServerWebExchange, token: String) {
        val claims = jwtUtil.extractAllClaims(token)
        exchange.request.mutate()
                .header("userId", claims["userId"].toString())
                .header("email", claims.subject)
                .build()
    }
}