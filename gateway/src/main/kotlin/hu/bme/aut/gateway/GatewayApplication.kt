package hu.bme.aut.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.WebSession
import reactor.core.publisher.Mono


@SpringBootApplication
@RestController
class GatewayApplication {

    @GetMapping(value = ["/token"])
    fun getHome(@RegisteredOAuth2AuthorizedClient authorizedClient: OAuth2AuthorizedClient): Mono<String> {
        return Mono.just(authorizedClient.accessToken.tokenValue)
    }

    @GetMapping("/")
    fun index(session: WebSession): Mono<String> {
        return Mono.just(session.id)
    }
}

fun main(args: Array<String>) {
    runApplication<GatewayApplication>(*args)
}

/*@RestController
class GreetingController {
	@RequestMapping("/greeting")
	fun greeting(
		@AuthenticationPrincipal oidcUser: OidcUser, model: Model,
		@RegisteredOAuth2AuthorizedClient("gateway") client: OAuth2AuthorizedClient
	): String {
		model.addAttribute("username", oidcUser.email)
		model.addAttribute("idToken", oidcUser.idToken)
		model.addAttribute("accessToken", client.accessToken)
		return "greeting"
	}


}*/
