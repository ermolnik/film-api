package com.ermolnik.api.details

import com.ermolnik.core.HOST_URL
import com.ermolnik.core.PORT
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.detailsApi() {
    routing {
        get("api/v1/details/{id}") {
            val details: Any = when (call.parameters["id"]) {
                "1" -> {
                    Details(
                        1,
                        "Гнев человеческий",
                        "Эйч — загадочный \u2028и холодный на вид джентльмен, но внутри него пылает жажда справедливости. Преследуя...",
                        3.0f,
                        18,
                        "Боевик",
                        "22.04.2021",
                        "$HOST_URL:$PORT/cover",
                        listOf(
                            Actor(
                                "Джейсон Стэйтем",
                                "$HOST_URL:$PORT/img_statham",
                            ),
                            Actor(
                                "Холт Маккэллани",
                                "$HOST_URL:$PORT/img_maccalany",
                            ),
                        ),
                    )
                }

                "2" -> {
                    Details(
                        2,
                        "Мортал Кобмат",
                        "Боец смешанных единоборств Коул Янг \u2028не раз соглашался проиграть за деньги. \u2028Он не знает о своем наследии...",
                        5.0f,
                        18,
                        "Боевик",
                        "22.04.2021",
                        "$HOST_URL:$PORT/cover",
                        listOf(
                            Actor(
                                "Джейсон Стэйтем",
                                "$HOST_URL:$PORT/img_statham",
                            ),
                            Actor(
                                "Холт Маккэллани",
                                "$HOST_URL:$PORT/img_maccalany",
                            ),
                        ),
                    )
                }

                "3" -> {
                    Details(
                        3,
                        "Упс...Приплыли",
                        "От Великого потопа зверей спас ковчег. Но спустя полгода скитаний они готовы сбежать \u2028с него куда угодно...",
                        3.5f,
                        18,
                        "Мультик",
                        "22.04.2021",
                        "$HOST_URL:$PORT/cover",
                        listOf(
                            Actor(
                                "Джейсон Стэйтем",
                                "$HOST_URL:$PORT/img_statham",
                            ),
                            Actor(
                                "Холт Маккэллани",
                                "$HOST_URL:$PORT/img_maccalany",
                            ),
                        ),
                    )
                }

                "4" -> {
                    Details(
                        4,
                        "The Box",
                        "Уличный музыкант знакомится \u2028с музыкальным продюсером, и они вдвоём отправляются \u2028в путешествие...",
                        4.2f,
                        12,
                        "Мелодрама",
                        "22.04.2021",
                        "$HOST_URL:$PORT/cover",
                        listOf(
                            Actor(
                                "Джейсон Стэйтем",
                                "$HOST_URL:$PORT/img_statham",
                            ),
                            Actor(
                                "Холт Маккэллани",
                                "$HOST_URL:$PORT/img_maccalany",
                            ),
                        ),
                    )
                }

                "5" -> {
                    Details(
                        5,
                        "Сага о Дэни Эрнандасе",
                        "Tekashi69 или \u2028Сикснайн — знаменитый бруклинский рэпер \u2028с радужными \u2028волосами — прогремел...",
                        5.0f,
                        16,
                        "Документальный",
                        "22.04.2021",
                        "$HOST_URL:$PORT/cover",
                        listOf(
                            Actor(
                                "Джейсон Стэйтем",
                                "$HOST_URL:$PORT/img_statham",
                            ),
                            Actor(
                                "Холт Маккэллани",
                                "$HOST_URL:$PORT/img_maccalany",
                            ),
                        ),
                    )
                }

                "6" -> {
                    Details(
                        6,
                        "Пчелка Майя",
                        "Когда упрямая пчелка Майя и ее лучший друг Вилли спасают принцессу-муравьишку, начинается сказочное...",
                        5.0f,
                        0,
                        "Мультик",
                        "22.04.2021",
                        "$HOST_URL:$PORT/cover",
                        listOf(
                            Actor(
                                "Джейсон Стэйтем",
                                "$HOST_URL:$PORT/img_statham",
                            ),
                            Actor(
                                "Холт Маккэллани",
                                "$HOST_URL:$PORT/img_maccalany",
                            ),
                        ),
                    )
                }

                else -> {
                    call.respondText(text = "500: No film with this id", status = HttpStatusCode.InternalServerError)
                }
            }
            call.respond(details)
        }
    }
}