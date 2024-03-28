package com.ermolnik.api.users

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    @SerialName("username")
    val username: String,

    @SerialName("password")
    val password: String,

    @SerialName("full_name")
    val fullName: String,

    @SerialName("email")
    val email: String,
)

@Serializable
data class UserResponse(
    @SerialName("username")
    val username: String,

    @SerialName("full_name")
    val fullName: String,

    @SerialName("email")
    val email: String
)