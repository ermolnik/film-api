package com.ermolnik.db

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.migrateDatabase() {
    transaction {
        SchemaUtils.create(
            Users,
            Accounts,
            Movies,
            AccountsMoviesToWatch,
            AccountsMovies,
        )
    }
}