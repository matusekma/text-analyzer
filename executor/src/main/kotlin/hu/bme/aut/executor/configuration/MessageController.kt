package hu.bme.aut.executor.configuration

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MessagesController {

    @GetMapping("/messages")
    fun getMessages() = arrayOf("Message 1", "Message 2", "Message 3")

    @GetMapping("/ping")
    fun ping(): String {
        val context: SecurityContext = SecurityContextHolder.getContext()
        val authentication: Authentication = context.authentication
        return "Scopes: " + authentication.authorities
    }
}