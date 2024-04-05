package com.ermolnik.api.users

import com.ermolnik.repository.CreateUserParams
import com.ermolnik.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.usersApi() {
    val userRepository = UserRepository()

    routing {
        // Create user
        post("/users") {
            val req = call.receive<CreateUserRequest>()

            // TODO: Hash password first!

            val arg = CreateUserParams(
                username = req.username,
                hashedPassword = req.password,
                fullName = req.fullName,
                email = req.email,
            )

            val user = userRepository.create(arg)

            val resp = CreateUserResponse(
                username = user,
            )
            call.respond(HttpStatusCode.Created, resp)
        }
    }
}