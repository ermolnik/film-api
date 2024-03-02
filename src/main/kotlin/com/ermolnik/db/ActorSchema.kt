package com.ermolnik.db

import io.ktor.server.application.*
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

@Serializable
internal data class ExposedActor(
    val id: Int,
    val filmId: Int,
    val fullName: String,
    val photo: String
)

internal object Actors : IntIdTable() {
    val filmId = integer("filmId")
        .uniqueIndex()
        .references(Films.id)
    val fullName = varchar("fullName", length = 128)
    val photo = varchar("photo", 128)

}

internal class ActorsDB(private val database: Database) {
    init {
        transaction(database) {
            SchemaUtils.create(Actors)
        }
    }

    suspend fun create(actor: ExposedActor): Int = dbQuery {
        Actors.insert {
            it[fullName] = actor.fullName
            it[photo] = actor.photo
        }[Actors.id].value
    }

    suspend fun read(id: Int): ExposedActor? {
        return dbQuery {
            Actors.select { Actors.id eq id }
                .map { it.map() }
                .singleOrNull()
        }
    }

    suspend fun readAll(): List<ExposedActor> {
        return dbQuery {
            Actors.selectAll().map { it.map() }
        }
    }

    suspend fun update(id: Int, actor: ExposedActor) {
        dbQuery {
            Actors.update({ Actors.id eq id }) {
                it[fullName] = actor.fullName
                it[photo] = actor.photo
            }
        }
    }

    suspend fun delete(id: Int) {
        dbQuery {
            Actors.deleteWhere { Actors.id.eq(id) }
        }
    }

    private fun ResultRow.map() = ExposedActor(
        this[Actors.filmId],
        0,
        this[Actors.fullName],
        this[Actors.photo],
    )
}

fun Application.actorsRouting(database: Database) {
    val actorsDB = ActorsDB(database)

}
