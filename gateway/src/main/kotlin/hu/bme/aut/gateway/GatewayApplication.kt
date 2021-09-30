package hu.bme.aut.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RestController


@SpringBootApplication
@RestController
class GatewayApplication

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
