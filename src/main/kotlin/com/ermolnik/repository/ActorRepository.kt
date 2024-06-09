package com.ermolnik.repository

import com.ermolnik.db.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class CreateActorParams(
    val fullName: String,
    val photoURL: String,
    val movieID: Int,
)

class ListActorsParams(
    val movie: String,
    val limit: Int,
    val offset: Int,
)

class ActorRepository {
    suspend fun create(arg: CreateActorParams): Int = dbQuery {
        val newActorID = Actors.insert {
            it[fullName] = arg.fullName
            it[photoURL] = arg.photoURL
        } get Actors.id

        MoviesActors.insert {
            it[movieID] = arg.movieID
            it[actorID] = newActorID
        }

        newActorID
    }

    suspend fun get(id: Int): Actor? = dbQuery {
        Actors.selectAll()
            .where {
                Actors.id eq id
            }
            .map {
                Actor(
                    id = it[Actors.id],
                    fullName = it[Actors.fullName],
                    photoURL = it[Actors.photoURL],
                )
            }
            .singleOrNull()
    }

    suspend fun list(arg: ListActorsParams): List<Actor> = dbQuery {
        val actorsTable = Actors.alias("a")
        val moviesTable = Movies.alias("m")

        MoviesActors
            .innerJoin(
                actorsTable,
                onColumn = { MoviesActors.actorID },
                otherColumn = { actorsTable[Actors.id] },
            )
            .innerJoin(
                moviesTable,
                onColumn = { MoviesActors.movieID },
                otherColumn = { moviesTable[Movies.id] },
            )
            .selectAll()
            .where {
                Movies.name eq arg.movie
            }
            .orderBy(Actors.id)
            .limit(arg.limit, offset = arg.offset.toLong())
            .map {
                Actor(
                    id = it[Actors.id],
                    fullName = it[Actors.fullName],
                    photoURL = it[Actors.photoURL],
                )
            }
    }

    suspend fun delete(id: Int): Int = dbQuery {
        Actors.deleteWhere {
            Actors.id eq id
        }
    }
}