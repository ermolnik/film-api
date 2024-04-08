package com.ermolnik.db

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

object Actors : Table("actors") {
    val id = integer("id").autoIncrement()
    val fullName = varchar("full_name", 255)
    val photoURL = varchar("photo_url", 255)

    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class Actor(
    @SerialName("id")
    val id: Int,

    @SerialName("full_name")
    val fullName: String,

    @SerialName("photo_url")
    val photoURL: String,
)
