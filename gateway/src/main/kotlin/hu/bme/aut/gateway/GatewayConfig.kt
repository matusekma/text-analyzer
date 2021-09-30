package hu.bme.aut.gateway

import hu.bme.aut.gateway.authentication.AuthenticationFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class GatewayConfig(private val authenticationFilter: AuthenticationFilter) {

    @Bean
    fun routeLocator(rb: RouteLocatorBuilder): RouteLocator =
            rb.routes()
                    .route("messages") { routeSpec ->
                        routeSpec.path("/messages")
                                .filters { f -> f.filter(authenticationFilter)}
                                .uri("http://localhost:8081")
                    }
                    .route("profanityfilter") { routeSpec ->
                        routeSpec.path("/executor/profanityfilter")
                                .filters { f -> f.filter(authenticationFilter)}
                                .uri("http://localhost:8081")
                    }
                    .route("ping") { routeSpec ->
                        routeSpec.path("/ping/**")
                                .uri("http://localhost:8081")
                    }
                    .route("register") { routeSpec ->
                        routeSpec.path("/register")
                                .uri("http://localhost:9000")
                    }
                    .route("login") { routeSpec ->
                        routeSpec.path("/login")
                                .uri("http://localhost:9000")
                    }
                    .build()
}