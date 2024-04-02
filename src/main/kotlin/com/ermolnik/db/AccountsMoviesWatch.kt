package com.ermolnik.db

import org.jetbrains.exposed.sql.Table

object AccountsMoviesWatch : Table("accounts_movies_watch") {
    val accountID = reference("account_id", Accounts.id)
    val movieID = reference("movie_id", Movies.id)
}