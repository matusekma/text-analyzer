package hu.bme.aut.textanalyzerauth.configuration

import hu.bme.aut.textanalyzerauth.auth.CustomUserDetailsManager
import hu.bme.aut.textanalyzerauth.repository.UserRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler

@Configuration
@EnableWebSecurity
class DefaultSecurityConfig(
    @Value("\${jwt.accessTokenCookieName}")
    private val accessTokenCookieName: String,
    private val userRepository: UserRepository
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
            .cors(withDefaults())
            .csrf { csrf -> csrf.disable() } //.ignoringAntMatchers("/api/admin/login")
            .authorizeRequests { authorizeRequests ->
                authorizeRequests
//                .antMatchers("/api/cases/*", "/api/cases/*/comments").hasAnyAuthority(Role.ASSISTANT.getValue(), Role.DOCTOR.getValue(), Role.PATIENT.getValue())
//                .antMatchers("/api/registration-requests/**", "/api/admin/cases").hasAnyAuthority(Role.ASSISTANT.getValue(), Role.DOCTOR.getValue())
//                .antMatchers("/api/assistants", "/api/cases/*/status").hasAuthority(Role.DOCTOR.getValue())
//                .antMatchers("/api/children", "/api/children/*/cases", "/api/patient/cases", "/api/patient/cases/*/files").hasAuthority(Role.PATIENT.getValue())
                    .antMatchers("/**").permitAll()
            }
            .sessionManagement { sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .logout {
                it.permitAll()
                it.logoutSuccessHandler(HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                it.logoutUrl("/logout")
                it.deleteCookies(accessTokenCookieName)
                it.invalidateHttpSession(false)
            }
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }

    @Bean
    override fun userDetailsService(): UserDetailsService =
        CustomUserDetailsManager(userRepository)

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authProvider(
        passwordEncoder: PasswordEncoder,
        userDetailsService: UserDetailsService
    ): DaoAuthenticationProvider? {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder)
        return authProvider
    }

/* @Bean
 fun corsConfigurationSource(): CorsConfigurationSource {
     val configuration = CorsConfiguration().apply {
         allowedOrigins = listOf("http://localhost:8080", "http://localhost:3000")
         allowedMethods = listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
         allowedHeaders = listOf("*")
     }
     val source = UrlBasedCorsConfigurationSource().apply {
         registerCorsConfiguration("/**", configuration)
     }
     return source
 }
 */
 */

    fun getLoggedInUserEmail(): String {
        val authentication = SecurityContextHolder.getContext().authentication
        val authenticatedUser = authentication.principal as User
        return authenticatedUser.username
    }
}