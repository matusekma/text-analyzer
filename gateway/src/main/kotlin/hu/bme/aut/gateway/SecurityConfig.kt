package hu.bme.aut.gateway

import org.springframework.context.annotation.Bean
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity -> web.ignoring().antMatchers("/webjars/**") }
    }


    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.csrf().disable()
                .authorizeExchange { authorizeExchange -> authorizeExchange
                        .pathMatchers("/register").permitAll()
                        .anyExchange().authenticated() }
                .oauth2Login(withDefaults())
                .oauth2Client(withDefaults())
        return http.build()
    }
}