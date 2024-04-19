package com.ermolnik.api.movies

import com.ermolnik.repository.MovieRepository
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Route.moviesApi() {
    val movieRepository = MovieRepository()

    // TODO: Implement routes for `/movies` endpoint.
}