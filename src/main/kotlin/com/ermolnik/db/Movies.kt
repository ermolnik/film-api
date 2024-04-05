package com.ermolnik.db

import com.ermolnik.db.serializer.KOffsetDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentTimestamp
import org.jetbrains.exposed.sql.kotlin.datetime.timestampWithTimeZone
import java.time.OffsetDateTime

object Movies : Table("movies") {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val posterURL = varchar("poster_url", 255)
    val createdAt = timestampWithTimeZone("created_at")
        .defaultExpression(CurrentTimestamp())

    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class Movie(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("poster_url")
    val posterURL: String,

    @SerialName("created_at")
    @Serializable(with = KOffsetDateTimeSerializer::class)
    val createdAt: OffsetDateTime,
)
