package hu.bme.aut.textanalyzerauth.configuration

import hu.bme.aut.textanalyzerauth.auth.CustomUserDetailsManager
import hu.bme.aut.textanalyzerauth.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class DefaultSecurityConfig(val userRepository: UserRepository) {

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf().disable()
                .authorizeRequests { authorizeRequests ->
                    authorizeRequests
                            .antMatchers("/register").permitAll()
                            .anyRequest().authenticated()
                }
                .formLogin(withDefaults())/*.loginProcessingUrl("/api/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .successHandler(authenticationHandler)
            .failureHandler(authenticationHandler)*/
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService =
            CustomUserDetailsManager(userRepository)


    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}