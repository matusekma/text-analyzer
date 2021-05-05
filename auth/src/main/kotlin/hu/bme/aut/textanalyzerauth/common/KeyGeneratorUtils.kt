package hu.bme.aut.textanalyzerauth.common

import java.lang.Exception
import java.security.KeyPair
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import java.security.KeyPairGenerator


class KeyGeneratorUtils private constructor() {

    companion object {
        fun generateSecretKey(): SecretKey {
            return try {
                KeyGenerator.getInstance("HmacSha256").generateKey()
            } catch (ex: Exception) {
                throw IllegalStateException(ex)
            }
        }

        fun generateRsaKey(): KeyPair {
            return try {
                val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
                keyPairGenerator.initialize(2048)
                keyPairGenerator.generateKeyPair()
            } catch (ex: Exception) {
                throw IllegalStateException(ex)
            }
        }
    }
}