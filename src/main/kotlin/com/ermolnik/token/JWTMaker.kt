package com.ermolnik.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import com.auth0.jwt.interfaces.Payload
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.config.*
import java.util.*

private const val minSecretKeySize = 32

class JWTMaker(applicationConfig: ApplicationConfig) {
    init {
        val secretKey = applicationConfig.property("jwt.secret").getString()
        require(secretKey.length >= minSecretKeySize) {
            "invalid key size: must be at least $minSecretKeySize characters"
        }
    }

    private val secret = applicationConfig.property("jwt.secret").getString()
    private val issuer = applicationConfig.property("jwt.issuer").getString()
    private val audience = applicationConfig.property("jwt.audience").getString()
    private val realm = applicationConfig.property("jwt.realm").getString()

    fun createToken(username: String, duration: Long): String {
        return JWT.create()
            .withAudience(audience)
            .withIssuer(issuer)
            .withClaim("username", username)
            .withExpiresAt(Date(System.currentTimeMillis() + duration))
            .sign(Algorithm.HMAC256(secret))
    }

    fun verifyToken(): JWTVerifier {
        return JWT
            .require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()
    }

    fun validateToken(payload: Payload): Principal? {
        val username = payload.getClaim("username").asString()

        if (username.isEmpty()) {
            return null
        }

        return JWTPrincipal(payload)
    }
}