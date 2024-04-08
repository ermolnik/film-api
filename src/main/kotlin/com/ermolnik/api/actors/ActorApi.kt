package com.ermolnik.api.actors

import com.ermolnik.repository.ActorRepository
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.actorApi() {
    val actorRepository = ActorRepository()

    routing {
        // TODO: Implement routes for `/actor` endpoint.
    }
}