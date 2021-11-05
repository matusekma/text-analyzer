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
                    .filters { f -> f.filter(authenticationFilter) }
                    .uri("http://localhost:8081")
            }
            .route("uploads") { routeSpec ->
                routeSpec.path("/uploads")
                    .filters { f -> f.filter(authenticationFilter) }
                    .uri("http://localhost:8081")

            }
            .route("uploadsById") { routeSpec ->
                routeSpec.path("/uploads/{id}")
                    .filters { f -> f.filter(authenticationFilter) }
                    .uri("http://localhost:8081")

            }
            .route("labels") { routeSpec ->
                routeSpec.path("/labels")
                    .filters { f -> f.filter(authenticationFilter) }
                    .uri("http://localhost:8081")

            }
            .route("pipelines") { routeSpec ->
                routeSpec.path("/pipelines")
                    .filters { f -> f.filter(authenticationFilter) }
                    .uri("http://localhost:8081")

            }
            .route("jobs") { routeSpec ->
                routeSpec.path("/jobs")
                    .filters { f -> f.filter(authenticationFilter) }
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
            .route("logout") { routeSpec ->
                routeSpec.path("/logout")
                    .uri("http://localhost:9000")
            }
            .route("me") { routeSpec ->
                routeSpec.path("/me")
                    .filters { f -> f.filter(authenticationFilter) }
                    .uri("http://localhost:9000")
            }
            .build()
}