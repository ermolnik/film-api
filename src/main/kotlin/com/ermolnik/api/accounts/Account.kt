package com.ermolnik.api.accounts

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateAccountRequest(
    @SerialName("owner")
    val owner: String,
)

@Serializable
data class CreateAccountResponse(
    @SerialName("owner")
    val owner: String,
)

@Serializable
data class GetAccountRequest(
    @SerialName("id")
    val id: Int,
)

@Serializable
data class ListAccountRequest(
    @SerialName("owner")
    val owner: String,

    @SerialName("page_id")
    val pageID: Int,

    @SerialName("page_size")
    val pageSize: Int,
)

@Serializable
data class DeleteAccountRequest(
    @SerialName("id")
    val id: Int,
)
