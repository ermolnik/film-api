package com.ermolnik.api.users

import com.ermolnik.api.users.routes.signIn
import com.ermolnik.api.users.routes.signUp
import com.ermolnik.repository.UserRepository
import com.ermolnik.api.users.auth.JwtService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import javax.security.sasl.AuthenticationException

fun Application.usersApi() {
    val userRepository = UserRepository()
    val jwtService = JwtService(
        application = this,
        repository = userRepository
    )

    authentication {
        jwt {
            realm = jwtService.realm
            verifier(jwtService.jwtVerifier)
            validate { credential ->
                jwtService.customValidator(credential)
            }
        }
    }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            if (cause is AuthenticationException) {
                call.respondText(
                    text = "${HttpStatusCode.Unauthorized.value} $cause",
                    status = HttpStatusCode.Unauthorized
                )
            }
        }
    }

    routing {
        signIn(userRepository, jwtService)
        signUp(userRepository, jwtService)
    }
}
