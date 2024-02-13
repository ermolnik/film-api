package com.ermolnik.api.fimls

import com.ermolnik.core.HOST_URL
import com.ermolnik.core.PORT
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.filmApi() {
    routing {
        get("/v1/films") {
            val film = listOf(
                Film(
                    1,
                    "Гнев человеческий",
                    "Эйч — загадочный \u2028и холодный на вид джентльмен, но внутри него пылает жажда справедливости. Преследуя...",
                    "$HOST_URL:$PORT/img_anger",
                    3.0f,
                    18
                ),
                Film(
                    2,
                    "Мортал Комбат",
                    "Боец смешанных единоборств Коул Янг \u2028не раз соглашался проиграть за деньги. \u2028Он не знает о своем наследии...",
                    "$HOST_URL:$PORT/img_mk",
                    5.0f,
                    18
                ),
                Film(
                    3,
                    "Упс... Приплыли",
                    "От Великого потопа зверей спас ковчег. Но спустя полгода скитаний они готовы сбежать \u2028с него куда угодно...",
                    "$HOST_URL:$PORT/img_ups",
                    3.5f,
                    18
                ),
                Film(
                    4,
                    "The Box",
                    "Уличный музыкант знакомится \u2028с музыкальным продюсером, и они вдвоём отправляются \u2028в путешествие...",
                    "$HOST_URL:$PORT/img_box",
                    4.2f,
                    12
                ),
                Film(
                    5,
                    "Сага о Дэни Эрнандасе",
                    "Tekashi69 или \u2028Сикснайн — знаменитый бруклинский рэпер \u2028с радужными \u2028волосами — прогремел...",
                    "$HOST_URL:$PORT/img_sixnine",
                    5.0f,
                    16
                ),
                Film(
                    6,
                    "Пчелка Майя",
                    "Когда упрямая пчелка Майя и ее лучший друг Вилли спасают принцессу-муравьишку, начинается сказочное...",
                    "$HOST_URL:$PORT/img_bee",
                    5.0f,
                    0
                ),
            )
            call.respond(film)
        }
    }
}