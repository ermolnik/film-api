package com.ermolnik.api

import com.ermolnik.api.accounts.accountsApi
import com.ermolnik.api.movies.moviesApi
import com.ermolnik.api.static.staticApi
import com.ermolnik.api.users.usersApi
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database

fun Application.configureRouting(database: Database) {
    usersApi(database)
    accountsApi(database)

    moviesApi(database)

    staticApi()
}
