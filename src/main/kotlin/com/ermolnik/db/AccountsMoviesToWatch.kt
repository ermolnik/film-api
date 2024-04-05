package com.ermolnik.db

import org.jetbrains.exposed.sql.Table

object AccountsMoviesToWatch : Table("accounts_movies_to_watch") {
    val accountID = reference("account_id", Accounts.id)
    val movieID = reference("movie_id", Movies.id)

    override val primaryKey = PrimaryKey(accountID, movieID)
}