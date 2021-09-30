package hu.bme.aut.textanalyzerauth.auth.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Function


@Service
class JwtUtilService(
        @Value("\${jwt.secret}")
        secret: String
) {
    private val secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))

    fun extractUserName(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    fun <T> extractClaim(token: String, claimsResolver: Function<Claims, T>): T {
        val claims: Claims = extractAllClaims(token)
        return claimsResolver.apply(claims)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .body
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    fun generateToken(userTokenData: UserTokenData): String {
        val claims = mapOf("userId" to userTokenData.userId)
        return createToken(claims, userTokenData.email)
    }

    private fun createToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer("textanalyzer")
                .setIssuedAt(Date())
                .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUserName(token)
        return username == userDetails.username && !isTokenExpired(token)
    }
}