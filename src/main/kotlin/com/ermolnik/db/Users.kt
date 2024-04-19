package com.ermolnik.db

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.CurrentTimestamp
import org.jetbrains.exposed.sql.kotlin.datetime.timestampWithTimeZone
import java.time.OffsetDateTime

object Users : Table("users") {
    val username = varchar("username", 24)
    val hashedPassword = varchar("hashed_password", 64)
    val fullName = varchar("full_name", 255)
    val email = varchar("email", 255)
    val passwordChangedAt = timestampWithTimeZone("password_changed_at")
        .clientDefault {
            OffsetDateTime.parse("0001-01-01T00:00:00Z")
        }
    val createdAt = timestampWithTimeZone("created_at")
        .defaultExpression(CurrentTimestamp())

    override val primaryKey = PrimaryKey(username)
}

data class User(
    val username: String,
    val hashedPassword: String,
    val fullName: String,
    val email: String,
    val passwordChangedAt: OffsetDateTime,
    val createdAt: OffsetDateTime,
)
