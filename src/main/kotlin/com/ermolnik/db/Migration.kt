package com.ermolnik.db

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.migrateDatabase() {
    transaction {
        // TODO: Remove to not drop the database after we implement migrations.
        SchemaUtils.dropDatabase()

        SchemaUtils.create(
            Users,
            Profiles,
            Movies,
            ProfilesMoviesToWatch,
            ProfilesMovies,
            Actors,
            MoviesActors,
        )
    }
}