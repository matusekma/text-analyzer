package hu.bme.aut.gateway

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GatewayConfig {

    @Bean
    fun routeLocator(rb: RouteLocatorBuilder): RouteLocator =
        rb.routes()
            .route("login-route") { routeSpec ->
                routeSpec
                    .path("/login")
                    .uri("lb://auth")
            }
            .build()
}