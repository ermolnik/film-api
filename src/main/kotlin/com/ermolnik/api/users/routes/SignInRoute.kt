package com.ermolnik.api.users.routes

import com.ermolnik.api.users.UserApiConst
import com.ermolnik.api.users.auth.Auth
import com.ermolnik.api.users.data.UserResponse
import com.ermolnik.api.users.data.UserSignInRequest
import com.ermolnik.api.users.auth.JwtService
import com.ermolnik.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.signIn(
    repository: UserRepository,
    jwtService: JwtService
) {
    post(UserApiConst.USER_SIGNIN_API) {
        val request = call.receive<UserSignInRequest>()
        val username = request.username
        val password = request.password
        when {
            username.isBlank() -> call.respond(
                HttpStatusCode.BadRequest,
                UserApiConst.USER_NAME_MISSING_ERROR
            )

            password.isBlank() -> call.respond(
                HttpStatusCode.BadRequest,
                UserApiConst.PASSWORD_MISSING_ERROR
            )

            else -> {
                when (val result = repository.findUser(username)) {
                    is UserRepository.Result.Success.Found -> {
                        val foundUser = result.user
                        val hashedPassword = Auth.convertToHash(password)

                        if (hashedPassword == foundUser.hashedPassword) {
                            val newToken = jwtService.generateToken(foundUser.userName)
                            call.respond(
                                HttpStatusCode.OK, UserResponse(
                                    username = foundUser.userName,
                                    email = foundUser.email,
                                    token = newToken,
                                    passwordChangedAt = foundUser.passwordChangedAt,
                                    createdAt = foundUser.createdAt
                                )
                            )
                        } else {
                            call.respond(
                                HttpStatusCode.Unauthorized,
                                UserApiConst.INCORRECT_INPUT_ERROR
                            )
                        }
                    }

                    else -> {
                        call.respond(
                            HttpStatusCode.Unauthorized,
                            UserApiConst.INCORRECT_INPUT_ERROR
                        )
                    }
                }
            }
        }
    }
}