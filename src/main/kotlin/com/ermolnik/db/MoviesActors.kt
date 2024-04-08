package com.ermolnik.db

import org.jetbrains.exposed.sql.Table

object MoviesActors : Table("movies_actors") {
    val movieID = reference("movie_id", Movies.id)
    val actorID = reference("actor_id", Actors.id)

    override val primaryKey = PrimaryKey(movieID, actorID)
}