package com.ermolnik.db

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

@Serializable
internal data class ExposedDetail(
    val id: Int,
    val description: String,
    val popularity: Double,
    val ageMark: Int,
    val genre: String,
    val date: String,
    val cover: String,
    val actors: List<ExposedActor>
)

object Details : Table() {
    val id = integer("id").autoIncrement()
    val description = varchar("description", length = 2048)
    val popularity = double("popularity")
    val ageMark = integer("ageMark")
    val genre = varchar("genre", 128)
    val date = varchar("date", 128)
    val cover = varchar("cover", 128)

    override val primaryKey = PrimaryKey(id)
}
internal class DetailsDB(private val database: Database) {

    init {
        transaction(database) {
            SchemaUtils.create(Details)
        }
    }

    suspend fun create(details: Details): Int = dbQuery {
        Details.insert {
            it[description] = details.description
            it[popularity] = details.popularity
            it[ageMark] = details.ageMark
            it[genre] = details.genre
            it[date] = details.date
            it[cover] = details.cover
        }[Details.id]
    }

    suspend fun read(id: Int): ExposedDetail? {
        return dbQuery {
            Details.select { Details.id eq id }
                .map {
                    it.map()
                }
                .singleOrNull()
        }
    }

    suspend fun readAll(): List<ExposedDetail> {
        return dbQuery {
            Details.selectAll().map {
                it.map()
            }
        }
    }

    suspend fun update(id: Int, detail: Details) {
        dbQuery {
            Details.update({ Details.id eq id }) {
                it[description] = detail.description
                it[popularity] = detail.popularity
                it[ageMark] = detail.ageMark
                it[genre] = detail.genre
                it[date] = detail.date
                it[cover] = detail.cover
            }
        }
    }

    suspend fun delete(id: Int) {
        dbQuery {
            Details.deleteWhere { Details.id.eq(id) }
        }
    }

    private fun ResultRow.map() = ExposedDetail(
        this[Details.id],
        this[Details.description],
        this[Details.popularity],
        this[Details.ageMark],
        this[Details.genre],
        this[Details.date],
        this[Details.cover],
        listOf()
    )
}
