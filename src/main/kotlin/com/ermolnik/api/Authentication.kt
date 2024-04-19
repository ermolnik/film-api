package com.ermolnik.api

import com.ermolnik.token.JWTMaker
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureAuthentication(): JWTMaker {
    val config = environment.config
    val tokenMaker = JWTMaker(config)

    install(Authentication) {
        jwt {
            val appRealm = config.property("jwt.realm").getString()

            realm = appRealm
            verifier(tokenMaker.verifyToken())
            validate { credential ->
                tokenMaker.validateToken(credential.payload)
            }
        }
    }

    return tokenMaker
}