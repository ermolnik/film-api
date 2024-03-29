package com.ermolnik.api.fimls

import kotlinx.serialization.Serializable

@Serializable
data class Film(
    val id: Long,
    val name: String,
    val description: String,
    val image: String,
    val popularity: Float,
    val ageMark: Int
)