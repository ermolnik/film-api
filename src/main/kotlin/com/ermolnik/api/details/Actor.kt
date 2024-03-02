package com.ermolnik.api.details

import kotlinx.serialization.Serializable

@Serializable
internal data class Actor(
    val fullName: String,
    val photo: String
)