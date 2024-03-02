package com.ermolnik.db

import com.ermolnik.core.IS_NEED_TO_SAVE_DATABASE
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    val databaseUrl = if (IS_NEED_TO_SAVE_DATABASE) "jdbc:h2:file:./build/db" else "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
    val database = Database.connect(
        url = databaseUrl,
        user = "root",
        driver = "org.h2.Driver",
        password = ""
    )

    usersRouting(database)
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }