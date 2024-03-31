package com.ermolnik

import com.ermolnik.core.plugins.configureHTTP
import com.ermolnik.core.plugins.configureMonitoring
import com.ermolnik.core.plugins.configureRouting
import com.ermolnik.core.plugins.configureSerialization
import com.ermolnik.db.configureDatabase
import io.ktor.server.application.*

fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    val db = configureDatabase()

    configureSerialization()
    configureMonitoring()
    configureHTTP()
    configureRouting(db)
}
