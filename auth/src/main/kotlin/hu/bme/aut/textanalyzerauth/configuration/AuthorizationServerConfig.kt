package hu.bme.aut.textanalyzerauth.configuration

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import hu.bme.aut.textanalyzerauth.common.Jwks
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.OidcScopes
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.config.ClientSettings
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings
import org.springframework.security.web.SecurityFilterChain
import java.util.*
import java.util.function.Supplier

@Configuration
class AuthorizationServerConfig {

    @Value("\${config.issuerUrl}")
    lateinit var issuerUrl: String

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    fun authorizationServerSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http)
        return http.formLogin(Customizer.withDefaults()).build()
    }

    @Bean
    fun registeredClientRepository(): RegisteredClientRepository {
        val gatewayClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("gateway")
                .clientSecret("secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://localhost:8080/login/oauth2/code/gateway")
                .redirectUri("http://localhost:8080/authorized")
                .scope(OidcScopes.OPENID)
                .scope("messages.read")
                .scope("executor.task")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build()
        /*val registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId("executor")
            .clientSecret("secret")
            .clientAuthenticationMethod(ClientAuthenticationMethod.POST)
            .clientAuthenticationMethod(ClientAuthenticationMethod.BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
            .redirectUri("http://localhost:8081/login/oauth2/code/executor")
            .redirectUri("http://localhost:8081/authorized")
            .scope(OidcScopes.OPENID)
            .scope("articles.read")
            .scope("message.read")
            .scope("message.write")
            .clientSettings { clientSettings -> clientSettings.requireUserConsent(true) }
            .build()*/
        return InMemoryRegisteredClientRepository(gatewayClient)
    }

    @Bean
    fun jwkSetProvider(): Supplier<JWKSet> {
        val rsaKey = Jwks.generateRSA()
        val jwkSet = JWKSet(rsaKey)
        return Supplier { jwkSet }
    }

    @Bean
    fun jwkSource(jwkSetProvider: Supplier<JWKSet>): JWKSource<SecurityContext> {
        return JWKSource<SecurityContext> { jwkSelector, _ -> jwkSelector.select(jwkSetProvider.get()) }
    }

    @Bean
    fun providerSettings(): ProviderSettings = ProviderSettings.builder().issuer(issuerUrl).build()
}