package com.ermolnik.api.static

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.staticApi() {
    routing {
        staticResources("/img_anger", "static", index = "img_anger.png")
        staticResources("/img_mk", "static", index = "img_mk.png")
        staticResources("/img_box", "static", index = "img_box.png")
        staticResources("/img_sixnine", "static", index = "img_sixnine.png")
        staticResources("/img_ups", "static", index = "img_ups.png")
        staticResources("/cover", "static", index = "cover.png")
        staticResources("/img_maccalany", "static", index = "img_maccalany.png")
        staticResources("/img_statham", "static", index = "img_statham.png")
    }
}
