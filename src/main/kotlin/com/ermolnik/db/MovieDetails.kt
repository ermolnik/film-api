package com.ermolnik.db

import com.ermolnik.db.serializer.KOffsetDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentTimestamp
import org.jetbrains.exposed.sql.kotlin.datetime.timestampWithTimeZone
import java.time.OffsetDateTime

object MovieDetails : Table("movie_details") {
    val id = integer("id").autoIncrement()
    val movieID = reference("movie_id", Movies.id)
    val description = text("description")
    val genre = varchar("genre", 255)
    val pegi = integer("pegi")
    val rating = float("rating")
    val releasedAt = timestampWithTimeZone("released_at")
        .clientDefault {
            OffsetDateTime.parse("0001-01-01T00:00:00Z")
        }
    val createdAt = timestampWithTimeZone("created_at")
        .defaultExpression(CurrentTimestamp())

    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class MovieDetail(
    @SerialName("id")
    val id: Int,

    @SerialName("movie_id")
    val movieID: Int,

    @SerialName("description")
    val description: String,

    @SerialName("genre")
    val genre: String,

    @SerialName("pegi")
    val pegi: Int,

    @SerialName("rating")
    val rating: Float,

    @SerialName("released_at")
    @Serializable(with = KOffsetDateTimeSerializer::class)
    val releasedAt: OffsetDateTime,

    @SerialName("created_at")
    @Serializable(with = KOffsetDateTimeSerializer::class)
    val createdAt: OffsetDateTime,
)
