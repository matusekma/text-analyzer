package hu.bme.aut.executor.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class OAuthSecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeRequests() { authorize ->
            authorize.antMatchers("/messages/**").hasAuthority("SCOPE_messages.read")
                .anyRequest().authenticated()
        }
            .oauth2ResourceServer().jwt()
        //.mvcMatchers("/messages/**").access("hasAuthority('SCOPE_articles.read')")
        return http.build()
    }
}