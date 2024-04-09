package com.ermolnik.api.users.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.ermolnik.db.User
import com.ermolnik.repository.UserRepository
import io.ktor.server.application.*
import io.ktor.server.auth.jwt.*

class JwtService(
    private val application: Application,
    private val repository: UserRepository
) {
    private val secret = getConfigProperty(JWT_SECRET_PROPERTY)
    private val issuer = getConfigProperty(JWT_ISSUER_PROPERTY)
    private val audience = getConfigProperty(JWT_AUDIENCE_PROPERTY)
    val realm = getConfigProperty(JWT_REALM_PROPERTY)

    private val algorithm = Algorithm.HMAC512(secret)
    init {
        Auth.init(secret)
    }

    val jwtVerifier: JWTVerifier =
        JWT
            .require(Algorithm.HMAC256(secret))
            .withAudience(audience)
            .withIssuer(issuer)
            .build()

    fun generateToken(userName: String) : String =
        JWT.create()
            .withSubject(SUBJECT)
            .withIssuer(issuer)
            .withClaim(ID, userName)
            .sign(algorithm)


   suspend fun customValidator(
        credential: JWTCredential,
    ): JWTPrincipal? {
        val username: String? = extractUsername(credential)
        val foundUser: User? = username?.let{
                when (val result = repository.findUser(it)){
                    is UserRepository.Result.Success.Found -> result.user
                    else -> null
                }
            }

        return foundUser?.let {
            if (audienceMatches(credential))
                JWTPrincipal(credential.payload)
            else
                null
        }
    }

    private fun audienceMatches(
        credential: JWTCredential,
    ): Boolean =
        credential.payload.audience.contains(audience)

    private fun getConfigProperty(path: String) =
        application.environment.config.property(path).getString()

    private fun extractUsername(credential: JWTCredential): String? =
        credential.payload.getClaim(USER_NAME_CLAIM).asString()

    companion object{
        private const val ID = "id"
        private const val SUBJECT = "Authentication"
        private const val JWT_SECRET_PROPERTY = "jwt.secret"
        private const val JWT_ISSUER_PROPERTY = "jwt.issuer"
        private const val JWT_AUDIENCE_PROPERTY = "jwt.audience"
        private const val JWT_REALM_PROPERTY = "jwt.realm"
        private const val USER_NAME_CLAIM = "username"
    }
}