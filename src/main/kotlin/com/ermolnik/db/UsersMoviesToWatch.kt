package com.ermolnik.db

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object UsersMoviesToWatch : Table("users_movies_to_watch") {
    val username = reference("username", Users.username, onDelete = ReferenceOption.CASCADE)
    val movieID = reference("movie_id", Movies.id, onDelete = ReferenceOption.CASCADE)

    init {
        index(columns = arrayOf(username))
        uniqueIndex(movieID, username)
    }
}