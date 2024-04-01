package com.ermolnik.db

import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

fun Application.configureDatabase(): Database {
    return with(environment.config) {
        val dbDriver = property("ktor.db-driver").getString()
        val dbSource = property("ktor.db-source").getString()
        val dbUser = property("ktor.db-user").getString()
        val dbPassword = property("ktor.db-password").getString()

        Database.connect(
            url = dbSource,
            driver = dbDriver,
            user = dbUser,
            password = dbPassword,
        )
    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }