package com.ermolnik

import com.ermolnik.core.HOST_URL
import com.ermolnik.core.PORT
import com.ermolnik.core.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = PORT, host = HOST_URL, module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureDatabases()
    configureMonitoring()
    configureHTTP()
    configureRouting()
}
