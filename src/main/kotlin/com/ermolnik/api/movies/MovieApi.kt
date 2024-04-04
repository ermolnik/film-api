package com.ermolnik.api.movies

import com.ermolnik.repository.MovieRepository
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.moviesApi() {
    val movieRepository = MovieRepository()

    routing {
        // TODO: Implement routes for `/movies` endpoint.
    }
}