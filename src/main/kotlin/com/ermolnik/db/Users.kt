package com.ermolnik.db

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

object Users : Table("users") {
    val username = varchar("username", 24)
    val hashedPassword = varchar("hashed_password", 64)
    val fullName = varchar("full_name", 255)
    val email = varchar("email", 255)

    override val primaryKey = PrimaryKey(username)
}

@Serializable
data class User(
    val username: String,
    val hashedPassword: String,
    val fullName: String,
    val email: String,
)
