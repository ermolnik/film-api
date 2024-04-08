package com.ermolnik.repository

import com.ermolnik.db.MovieDetail
import com.ermolnik.db.MovieDetails
import com.ermolnik.db.dbQuery
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class CreateMovieDetailParams(
    val movieID: Int,
    val description: String,
    val genre: String,
    val pegi: Int,
    val rating: Float,
)

class ListMovieDetailsParams(
    val limit: Int,
    val offset: Int,
)

class MovieDetailRepository {
    suspend fun create(arg: CreateMovieDetailParams): Int = dbQuery {
        MovieDetails.insert {
            it[movieID] = arg.movieID
            it[description] = arg.description
            it[genre] = arg.genre
            it[pegi] = arg.pegi
            it[rating] = arg.rating
        } get MovieDetails.id
    }

    suspend fun get(id: Int): MovieDetail? = dbQuery {
        MovieDetails.selectAll()
            .where {
                MovieDetails.id eq id
            }
            .map {
                MovieDetail(
                    id = it[MovieDetails.id],
                    movieID = it[MovieDetails.movieID],
                    description = it[MovieDetails.description],
                    genre = it[MovieDetails.genre],
                    pegi = it[MovieDetails.pegi],
                    rating = it[MovieDetails.rating],
                    releasedAt = it[MovieDetails.releasedAt],
                    createdAt = it[MovieDetails.createdAt],
                )
            }
            .singleOrNull()
    }

    suspend fun find(movieID: Int): MovieDetail? = dbQuery {
        MovieDetails.selectAll()
            .where {
                MovieDetails.movieID eq movieID
            }
            .map {
                MovieDetail(
                    id = it[MovieDetails.id],
                    movieID = it[MovieDetails.movieID],
                    description = it[MovieDetails.description],
                    genre = it[MovieDetails.genre],
                    pegi = it[MovieDetails.pegi],
                    rating = it[MovieDetails.rating],
                    releasedAt = it[MovieDetails.releasedAt],
                    createdAt = it[MovieDetails.createdAt],
                )
            }
            .singleOrNull()
    }

    suspend fun list(arg: ListMovieDetailsParams): List<MovieDetail> = dbQuery {
        MovieDetails.selectAll()
            .orderBy(MovieDetails.id)
            .limit(arg.limit, offset = arg.offset.toLong())
            .map {
                MovieDetail(
                    id = it[MovieDetails.id],
                    movieID = it[MovieDetails.movieID],
                    description = it[MovieDetails.description],
                    genre = it[MovieDetails.genre],
                    pegi = it[MovieDetails.pegi],
                    rating = it[MovieDetails.rating],
                    releasedAt = it[MovieDetails.releasedAt],
                    createdAt = it[MovieDetails.createdAt],
                )
            }
    }
}