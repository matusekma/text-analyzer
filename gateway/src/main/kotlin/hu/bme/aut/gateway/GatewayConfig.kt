package hu.bme.aut.gateway

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory

import org.springframework.beans.factory.annotation.Autowired


@Configuration
class GatewayConfig(

    @Autowired
    private val filterFactory: TokenRelayGatewayFilterFactory
) {

    @Bean
    fun routeLocator(rb: RouteLocatorBuilder): RouteLocator =
        rb.routes()
            .route("messages") { routeSpec ->
                routeSpec.path("/messages")
                    .filters { f ->
                        f.filters(filterFactory.apply())
                            .removeRequestHeader("Cookie")
                    }
                    .uri("lb://executor:8081")
            }.route("profanityfilter") { routeSpec ->
                routeSpec.path("/executor/profanityfilter")
                    .filters { f ->
                        f.filters(filterFactory.apply())
                            .removeRequestHeader("Cookie")
                            .setPath("/profanityfilter")
                    }
                    .uri("lb://executor:8081")
            }
            .route("ping") { routeSpec ->
                routeSpec.path("/ping/**")
                    .filters { f ->
                        f.filters(filterFactory.apply())
                            .removeRequestHeader("Cookie")
                    }
                    .uri("lb://executor:8081")
            }
            .route("messages") { routeSpec ->
                routeSpec.path("/register")
                    .uri("lb://auth:9000")
            }
            .build()
}