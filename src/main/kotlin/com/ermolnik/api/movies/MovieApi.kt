package com.ermolnik.api.movies

import com.ermolnik.repository.MovieRepository
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database

fun Application.moviesApi(database: Database) {
    val movieRepository = MovieRepository(database)

    routing {
        // TODO: Implement routes for `/movies` endpoint.
    }
}