package com.ermolnik.db

import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

fun Application.configureDatabase(): Database {
    val dbDriver = environment.config.property("ktor.db-driver").getString()
    val dbSource = environment.config.property("ktor.db-source").getString()
    val dbUser = environment.config.property("ktor.db-user").getString()
    val dbPassword = environment.config.property("ktor.db-password").getString()

    val db = Database.connect(
        url = dbSource,
        driver = dbDriver,
        user = dbUser,
        password = dbPassword,
    )

    return db
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }