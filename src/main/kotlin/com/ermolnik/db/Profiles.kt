package com.ermolnik.db

import com.ermolnik.db.serializer.KOffsetDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentTimestamp
import org.jetbrains.exposed.sql.kotlin.datetime.timestampWithTimeZone
import java.time.OffsetDateTime

object Profiles : Table("profiles") {
    val id = integer("id").autoIncrement()
    val owner = reference("owner", Users.username, onDelete = ReferenceOption.CASCADE)
    val createdAt = timestampWithTimeZone("created_at")
        .defaultExpression(CurrentTimestamp())

    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class Profile(
    @SerialName("id")
    val id: Int,

    @SerialName("owner")
    val owner: String,

    @SerialName("created_at")
    @Serializable(with = KOffsetDateTimeSerializer::class)
    val createdAt: OffsetDateTime,
)
