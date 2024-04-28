package com.ermolnik.api.profiles

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateProfileRequest(
    @SerialName("owner")
    val owner: String,
)

@Serializable
data class CreateProfileResponse(
    @SerialName("owner")
    val owner: String,
)

@Serializable
data class GetProfileRequest(
    @SerialName("id")
    val id: Int,
)

@Serializable
data class ListProfileRequest(
    @SerialName("owner")
    val owner: String,

    @SerialName("page_id")
    val pageID: Int,

    @SerialName("page_size")
    val pageSize: Int,
)

@Serializable
data class DeleteProfileRequest(
    @SerialName("id")
    val id: Int,
)
