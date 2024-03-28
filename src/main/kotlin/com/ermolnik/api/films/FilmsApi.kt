package com.ermolnik.api.films

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.filmApi() {
    routing {
        get("api/v1/films") {
            val film = listOf(
                Film(
                    1,
                    "Гнев человеческий",
                    "Эйч — загадочный \u2028и холодный на вид джентльмен, но внутри него пылает жажда справедливости. Преследуя...",
                    "https://upload.wikimedia.org/wikipedia/ru/5/55/%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_%28%D0%BF%D0%BE%D1%81%D1%82%D0%B5%D1%80%29.png",
                    3.0f,
                    18
                ),
                Film(
                    2,
                    "Мортал Комбат",
                    "Боец смешанных единоборств Коул Янг \u2028не раз соглашался проиграть за деньги. \u2028Он не знает о своем наследии...",
                    "https://upload.wikimedia.org/wikipedia/ru/5/55/%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_%28%D0%BF%D0%BE%D1%81%D1%82%D0%B5%D1%80%29.png",
                    5.0f,
                    18
                ),
                Film(
                    3,
                    "Упс... Приплыли",
                    "От Великого потопа зверей спас ковчег. Но спустя полгода скитаний они готовы сбежать \u2028с него куда угодно...",
                    "https://upload.wikimedia.org/wikipedia/ru/5/55/%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_%28%D0%BF%D0%BE%D1%81%D1%82%D0%B5%D1%80%29.png",
                    3.5f,
                    18
                ),
                Film(
                    4,
                    "The Box",
                    "Уличный музыкант знакомится \u2028с музыкальным продюсером, и они вдвоём отправляются \u2028в путешествие...",
                    "https://upload.wikimedia.org/wikipedia/ru/5/55/%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_%28%D0%BF%D0%BE%D1%81%D1%82%D0%B5%D1%80%29.png",
                    4.2f,
                    12
                ),
                Film(
                    5,
                    "Сага о Дэни Эрнандасе",
                    "Tekashi69 или \u2028Сикснайн — знаменитый бруклинский рэпер \u2028с радужными \u2028волосами — прогремел...",
                    "https://upload.wikimedia.org/wikipedia/ru/5/55/%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_%28%D0%BF%D0%BE%D1%81%D1%82%D0%B5%D1%80%29.png",
                    5.0f,
                    16
                ),
                Film(
                    6,
                    "Пчелка Майя",
                    "Когда упрямая пчелка Майя и ее лучший друг Вилли спасают принцессу-муравьишку, начинается сказочное...",
                    "https://upload.wikimedia.org/wikipedia/ru/5/55/%D0%93%D0%BD%D0%B5%D0%B2_%D1%87%D0%B5%D0%BB%D0%BE%D0%B2%D0%B5%D1%87%D0%B5%D1%81%D0%BA%D0%B8%D0%B9_%28%D0%BF%D0%BE%D1%81%D1%82%D0%B5%D1%80%29.png",
                    5.0f,
                    0
                ),
            )
            call.respond(film)
        }
    }
}