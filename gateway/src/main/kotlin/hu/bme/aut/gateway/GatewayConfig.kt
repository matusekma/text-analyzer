package hu.bme.aut.gateway

import hu.bme.aut.gateway.authentication.AuthenticationFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class GatewayConfig(
    @Value("\${services.auth.url}")
    private val authUrl: String,
    @Value("\${services.executor.url}")
    private val executorUrl: String,
    private val authenticationFilter: AuthenticationFilter
) {

    @Bean
    fun routeLocator(rb: RouteLocatorBuilder): RouteLocator =
        rb.routes()
            .route("uploads") { routeSpec ->
                routeSpec.path("/uploads")
                    .filters { f -> f.filter(authenticationFilter) }
                    .uri(executorUrl)

            }
            .route("uploadsById") { routeSpec ->
                routeSpec.path("/uploads/{id}")
                    .filters { f -> f.filter(authenticationFilter) }
                    .uri(executorUrl)

            }
            .route("labels") { routeSpec ->
                routeSpec.path("/labels")
                    .filters { f -> f.filter(authenticationFilter) }
                    .uri(executorUrl)

            }
            .route("pipelines") { routeSpec ->
                routeSpec.path("/pipelines")
                    .filters { f -> f.filter(authenticationFilter) }
                    .uri(executorUrl)
            }
            .route("jobs") { routeSpec ->
                routeSpec.path("/jobs")
                    .filters { f -> f.filter(authenticationFilter) }
                    .uri(executorUrl)
            }
            .route("asr") { routeSpec ->
                routeSpec.path("/asr")
                    .filters { f -> f.filter(authenticationFilter) }
                    .uri(executorUrl)
            }
            .route("register") { routeSpec ->
                routeSpec.path("/register")
                    .uri(authUrl)
            }
            .route("login") { routeSpec ->
                routeSpec.path("/login")
                    .uri(authUrl)
            }
            .route("logout") { routeSpec ->
                routeSpec.path("/logout")
                    .uri(authUrl)
            }
            .route("me") { routeSpec ->
                routeSpec.path("/me")
                    .filters { f -> f.filter(authenticationFilter) }
                    .uri(authUrl)
            }
            .build()
}