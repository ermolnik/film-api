package com.ermolnik.api.users.data

import com.ermolnik.db.serializer.KOffsetDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

@Serializable
data class CreateUserRequest(
    @SerialName("username")
    val username: String,

    @SerialName("password")
    val password: String,

    @SerialName("email")
    val email: String,
)

@Serializable
data class CreateUserResponse(
    @SerialName("username")
    val username: String,

    @SerialName("token")
    val token: String
)

@Serializable
data class UserResponse(
    @SerialName("username")
    val username: String,

    @SerialName("email")
    val email: String,

    @SerialName("token")
    val token: String,

    @SerialName("password_changed_at")
    @Serializable(with = KOffsetDateTimeSerializer::class)
    val passwordChangedAt: OffsetDateTime,

    @SerialName("created_at")
    @Serializable(with = KOffsetDateTimeSerializer::class)
    val createdAt: OffsetDateTime,
)

@Serializable
data class UserSignInRequest(
    @SerialName("username")
    val username: String,

    @SerialName("password")
    val password: String
)