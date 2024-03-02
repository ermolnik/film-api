package com.ermolnik.db

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

@Serializable
internal data class ExposedFilm(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val popularity: Double,
    val ageMark: Int,
    val actor: List<Int>
)

internal object Films : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", length = 128)
    val description = varchar("description", 2048)
    val image = varchar("image", 128)
    val popularity = double("popularity")
    val ageMark = integer("ageMark")
    val actors = reference("actor_id", Actors)

    override val primaryKey = PrimaryKey(id)
}

internal class FilmsDB(private val database: Database) {

    init {
        transaction(database) {
            SchemaUtils.create(Films)
        }
    }

    suspend fun create(film: ExposedFilm): Int = dbQuery {
        Films.insert {
            it[name] = film.name
            it[description] = film.description
        }[Films.id]
    }

    suspend fun read(id: Int): ExposedFilm? {
        return dbQuery {
            Films.select { Films.id eq id }
                .map { it.map() }
                .singleOrNull()
        }
    }

    suspend fun readAll(): List<ExposedFilm> {
        return dbQuery {
            Films.selectAll().map { it.map() }
        }
    }

    suspend fun update(id: Int, film: ExposedFilm) {
        dbQuery {
            Films.update({ Films.id eq id }) {
                it[name] = film.name
                it[description] = film.description
                it[image] = film.image
                it[popularity] = film.popularity
                it[ageMark] = film.ageMark
            }
        }
    }

    suspend fun delete(id: Int) {
        dbQuery {
            Films.deleteWhere { Films.id.eq(id) }
        }
    }

    private fun ResultRow.map() = ExposedFilm(
        this[Films.id],
        this[Films.name],
        this[Films.description],
        this[Films.image],
        this[Films.popularity],
        this[Films.ageMark],
        listOf()
    )
}
