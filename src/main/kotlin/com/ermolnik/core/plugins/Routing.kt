package com.ermolnik.core.plugins

import com.ermolnik.api.details.detailsApi
import com.ermolnik.api.fimls.filmApi
import com.ermolnik.api.static.staticApi
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    detailsApi()
    filmApi()
    staticApi()
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
