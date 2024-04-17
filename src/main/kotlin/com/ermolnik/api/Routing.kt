package com.ermolnik.api

import com.ermolnik.api.accounts.accountsApi
import com.ermolnik.api.movies.moviesApi
import com.ermolnik.api.static.staticApi
import com.ermolnik.api.users.usersApi
import com.ermolnik.token.JWTMaker
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.routing.*

fun Application.configureRouting(tokenMaker: JWTMaker) {
    routing {
        usersApi(tokenMaker)

        authenticate {
            accountsApi()
            moviesApi()
        }
    }

    // TODO: Delete `static` routes with resources.
    staticApi()
}
