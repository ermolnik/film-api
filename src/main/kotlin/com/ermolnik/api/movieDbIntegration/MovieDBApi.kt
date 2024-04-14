package com.ermolnik.api.movieDbIntegration

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

const val API_KEY =
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4YThmMmRmMDkyYzMxMDI1YjcwYTE1MzExNmYxYmEwOCIsInN1YiI6IjYzZDNmNjUzNDU4MTk5MDA4NGMxMTc3MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.d2wP7va3n7uHoPoKTy2Wx37S-IxsGmEaypGofF_4JE0"

val client = HttpClient(CIO) {
    install(Logging) {
        level = LogLevel.INFO
    }
    install(ContentNegotiation) {
        json()
    }
}

suspend fun getFilms(): MovieDBDTO =
    client.request("https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc") {
        method = HttpMethod.Get
        headers {
            append(HttpHeaders.Authorization, "Bearer $API_KEY")
            append(HttpHeaders.Accept, "application/json")
        }
    }.body()

