package com.ermolnik

import com.ermolnik.api.configureHTTP
import com.ermolnik.api.configureMonitoring
import com.ermolnik.api.configureRouting
import com.ermolnik.api.configureSerialization
import com.ermolnik.api.movieDbIntegration.getFilms
import com.ermolnik.db.configureDatabase
import com.ermolnik.db.migrateDatabase
import io.ktor.server.application.*
import kotlinx.coroutines.launch

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    val db = configureDatabase()
    migrateDatabase()

    configureSerialization()
    configureMonitoring()
    configureHTTP()

    configureRouting()
}
