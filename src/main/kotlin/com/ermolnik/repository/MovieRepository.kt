package com.ermolnik.repository

import com.ermolnik.db.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class CreateMovieParams(
    val accountID: Int,
    val name: String,
    val posterURL: String,
)

class ListMoviesParams(
    val owner: String,
    val limit: Int,
    val offset: Int,
)

class MovieRepository {
    suspend fun create(arg: CreateMovieParams): Int = dbQuery {
        val newMovieID = Movies.insert {
            it[name] = arg.name
            it[posterURL] = arg.posterURL
        } get Movies.id

        AccountsMovies.insert {
            it[accountID] = arg.accountID
            it[movieID] = newMovieID
        }

        newMovieID
    }

    suspend fun get(id: Int): Movie? = dbQuery {
        Movies.selectAll()
            .where {
                Movies.id eq id
            }
            .map {
                Movie(
                    id = it[Movies.id],
                    name = it[Movies.name],
                    posterURL = it[Movies.posterURL],
                    createdAt = it[Movies.createdAt],
                )
            }
            .singleOrNull()
    }

    suspend fun list(arg: ListMoviesParams): List<Movie> = dbQuery {
        val moviesTable = Movies.alias("m")
        val accountsTable = Accounts.alias("a")

        AccountsMovies
            .innerJoin(
                moviesTable,
                onColumn = { AccountsMovies.movieID },
                otherColumn = { moviesTable[Movies.id] },
            )
            .innerJoin(
                accountsTable,
                onColumn = { AccountsMovies.accountID },
                otherColumn = { accountsTable[Accounts.id] },
            )
            .selectAll()
            .where {
                Accounts.owner eq arg.owner
            }
            .orderBy(Movies.id)
            .limit(arg.limit, offset = arg.offset.toLong())
            .map {
                Movie(
                    id = it[Movies.id],
                    name = it[Movies.name],
                    posterURL = it[Movies.posterURL],
                    createdAt = it[Movies.createdAt],
                )
            }
    }

    suspend fun delete(id: Int): Int = dbQuery {
        Movies.deleteWhere {
            Movies.id eq id
        }

        AccountsMovies.deleteWhere {
            AccountsMovies.movieID eq id
        }

        MovieDetails.deleteWhere {
            MovieDetails.movieID eq id
        }
    }
}