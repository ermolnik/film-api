package com.ermolnik.api.moviedetails

import com.ermolnik.repository.MovieDetailRepository
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.movieDetailApi() {
    val movieDetailRepository = MovieDetailRepository()

    routing {
        // TODO: Implement routes for `/movie-details` endpoint.
    }
}