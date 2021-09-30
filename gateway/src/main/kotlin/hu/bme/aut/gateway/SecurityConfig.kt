package hu.bme.aut.gateway


//@EnableWebFluxSecurity
class SecurityConfig {

    /*@Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http.csrf().disable()
                .authorizeExchange { authorizeExchange ->
                    authorizeExchange
                            .pathMatchers("/register", "/login").permitAll()
                            .anyExchange().authenticated()
                }
        return http.build()
    }*/
}