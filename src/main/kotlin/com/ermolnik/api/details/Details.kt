package com.ermolnik.api.details

import kotlinx.serialization.Serializable

@Serializable
internal data class Details(
    val id: Long,
    val name: String,
    val description: String,
    val popularity: Float,
    val ageMark: Int,
    val genre: String,
    val date: String,
    val cover: String,
    val actors: List<Actor> = listOf()
)