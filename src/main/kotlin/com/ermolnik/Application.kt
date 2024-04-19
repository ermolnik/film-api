package com.ermolnik

import com.ermolnik.api.*
import com.ermolnik.db.configureDatabase
import com.ermolnik.db.migrateDatabase
import io.ktor.server.application.*

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    val db = configureDatabase()
    migrateDatabase()

    configureSerialization()
    configureMonitoring()
    configureHTTP()

    val tokenMaker = configureAuthentication()
    configureRouting(tokenMaker)
}
