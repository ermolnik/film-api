package com.ermolnik.api.users

import com.ermolnik.repository.CreateUserParams
import com.ermolnik.repository.UserRepository
import com.ermolnik.token.JWTMaker
import com.ermolnik.util.checkPassword
import com.ermolnik.util.hashPassword
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.usersApi(tokenMaker: JWTMaker) {
    val userRepository = UserRepository()

    // Create user
    post("/users") {
        val req = call.receive<CreateUserRequest>()

        val hashedPassword = hashPassword(req.password)

        val arg = CreateUserParams(
            username = req.username,
            hashedPassword = hashedPassword,
            fullName = req.fullName,
            email = req.email,
        )

        val user = userRepository.create(arg)

        val resp = CreateUserResponse(
            username = user,
        )
        call.respond(HttpStatusCode.Created, resp)
    }

    // Register user
    post("/users/register") {
        // TODO: Implement request validation with the RequestValidation plugin.
        val req = call.receive<RegisterUserRequest>()

        val hashedPassword = hashPassword(req.password)

        val arg = CreateUserParams(
            username = req.username,
            hashedPassword = hashedPassword,
            fullName = req.fullName,
            email = req.email,
        )

        val user = userRepository.create(arg)

        // TODO: Use `UserResponse` class to return in the response.
        val resp = CreateUserResponse(
            username = user,
        )
        call.respond(HttpStatusCode.OK, resp)
    }

    // Login user
    post("/users/login") {
        // TODO: Implement request validation with the RequestValidation plugin.
        val req = call.receive<LoginUserRequest>()

        val user = userRepository.get(req.username)
        if (user == null) {
            // TODO: Add custom error message.
            call.respond(HttpStatusCode.NotFound)
            return@post
        }

        val isVerifiedPassword = checkPassword(req.password, user.hashedPassword)
        if (!isVerifiedPassword) {
            // TODO: Add custom error message.
            call.respond(HttpStatusCode.Unauthorized)
            return@post
        }

        // TODO: Implement Config class.
        val duration = application.environment.config.property("jwt.duration")
            .getString()
            .toInt()
        val accessToken = tokenMaker.createToken(
            user.username,
            duration * 60 * 1000L,
        )

        val resp = LoginUserResponse(
            accessToken = accessToken,
            user = UserResponse(
                username = user.username,
                fullName = user.fullName,
                email = user.email,
                passwordChangedAt = user.passwordChangedAt,
                createdAt = user.createdAt,
            ),
        )
        call.respond(HttpStatusCode.OK, resp)
    }
}
