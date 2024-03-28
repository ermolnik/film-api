package com.ermolnik.api.details

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
                        "https://upload.wikimedia.org/wikipedia/ru/5/55/%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_%28%D0%BF%D0%BE%D1%81%D1%82%D0%B5%D1%80%29.png",
                        listOf(
                            Actor(
                                "Джейсон Стэйтем",
                                "https://upload.wikimedia.org/wikipedia/commons/d/d3/Jason_Statham_2018.jpg",
                            ),
                            Actor(
                                "Холт Маккэллани",
                                "https://upload.wikimedia.org/wikipedia/commons/d/d3/Jason_Statham_2018.jpg",
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
                        "https://upload.wikimedia.org/wikipedia/ru/5/55/%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_%28%D0%BF%D0%BE%D1%81%D1%82%D0%B5%D1%80%29.png",
                        listOf(
                            Actor(
                                "Джейсон Стэйтем",
                                "https://upload.wikimedia.org/wikipedia/commons/d/d3/Jason_Statham_2018.jpg",
                            ),
                            Actor(
                                "Холт Маккэллани",
                                "https://upload.wikimedia.org/wikipedia/commons/d/d3/Jason_Statham_2018.jpg",
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
                        "https://ru.wikipedia.org/wiki/%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9#/media/%D0%A4%D0%B0%D0%B9%D0%BB:%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_(%D0%BF%D0%BE%D1%81%D1%82%D0%B5%D1%80).png",
                        listOf(
                            Actor(
                                "Джейсон Стэйтем",
                                "https://upload.wikimedia.org/wikipedia/commons/d/d3/Jason_Statham_2018.jpg",
                            ),
                            Actor(
                                "Холт Маккэллани",
                                "https://upload.wikimedia.org/wikipedia/commons/d/d3/Jason_Statham_2018.jpg",
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
                        "https://ru.wikipedia.org/wiki/%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9#/media/%D0%A4%D0%B0%D0%B9%D0%BB:%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_(%D0%BF%D0%BE%D1%81%D1%82%D0%B5%D1%80).png",
                        listOf(
                            Actor(
                                "Джейсон Стэйтем",
                                "https://upload.wikimedia.org/wikipedia/commons/d/d3/Jason_Statham_2018.jpg",
                            ),
                            Actor(
                                "Холт Маккэллани",
                                "https://upload.wikimedia.org/wikipedia/commons/d/d3/Jason_Statham_2018.jpg",
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
                        "https://ru.wikipedia.org/wiki/%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9#/media/%D0%A4%D0%B0%D0%B9%D0%BB:%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_(%D0%BF%D0%BE%D1%81%D1%82%D0%B5%D1%80).png",
                        listOf(
                            Actor(
                                "Джейсон Стэйтем",
                                "https://upload.wikimedia.org/wikipedia/commons/d/d3/Jason_Statham_2018.jpg",
                            ),
                            Actor(
                                "Холт Маккэллани",
                                "https://upload.wikimedia.org/wikipedia/commons/d/d3/Jason_Statham_2018.jpg",
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
                        "https://ru.wikipedia.org/wiki/%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9#/media/%D0%A4%D0%B0%D0%B9%D0%BB:%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_(%D0%BF%D0%BE%D1%81%D1%82%D0%B5%D1%80).png",
                        listOf(
                            Actor(
                                "Джейсон Стэйтем",
                                "https://upload.wikimedia.org/wikipedia/commons/d/d3/Jason_Statham_2018.jpg",
                            ),
                            Actor(
                                "Холт Маккэллани",
                                "https://upload.wikimedia.org/wikipedia/commons/d/d3/Jason_Statham_2018.jpg",
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