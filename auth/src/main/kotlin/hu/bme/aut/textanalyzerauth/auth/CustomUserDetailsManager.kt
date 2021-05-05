package hu.bme.aut.textanalyzerauth.auth

import hu.bme.aut.textanalyzerauth.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class CustomUserDetailsManager(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user: hu.bme.aut.textanalyzerauth.domain.User =
            userRepository.findByEmail(email) ?: throw UsernameNotFoundException("User not found.")

        return User.builder()
            .username(user.email)
            .password(user.password)
            .authorities(user.role.toString())
            .build()
    }
}