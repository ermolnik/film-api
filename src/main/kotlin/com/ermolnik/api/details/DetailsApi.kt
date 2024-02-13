package com.ermolnik.api.details

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.detailsApi() {
    routing {
        get("/v1/details/id") {
            val details = Details(
                "baeldung",
                "baeldung.com",
                5.5f,
                18,
                "",
                "",
                ""
            )
            call.respond(details)
        }
    }
}