package com.ermolnik.api.users.routes

import com.ermolnik.api.users.auth.Auth
import com.ermolnik.api.users.data.CreateUserRequest
import com.ermolnik.api.users.data.CreateUserResponse
import com.ermolnik.api.users.UserApiConst
import com.ermolnik.repository.CreateUserParams
import com.ermolnik.repository.UserRepository
import com.ermolnik.api.users.auth.JwtService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.signUp(
    repository: UserRepository,
    jwtService: JwtService
) {
    post(UserApiConst.USER_SIGNUP_API) {
        val request = call.receive<CreateUserRequest>()
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
                try {
                    when (val resultFindUser = repository.findUser(username)) {
                        is UserRepository.Result.Success.Found -> {
                            call.respond(
                                HttpStatusCode.Found,
                                UserApiConst.USER_REGISTERED_ERROR
                            )
                        }

                        else -> {
                            val jwtToken = jwtService.generateToken(request.username)
                            val hash = Auth.convertToHash(password)

                            when (val newUserResult = repository.create(
                                CreateUserParams(
                                    username = request.username,
                                    hashedPassword = hash,
                                    email = request.email ?: "",
                                    token = jwtToken
                                )
                            )
                            ) {
                                is UserRepository.Result.Success.Created -> {
                                    call.respond(
                                        HttpStatusCode.Created, CreateUserResponse(
                                            username = newUserResult.userName,
                                            token = jwtToken
                                        )
                                    )
                                }

                                else -> {
                                    call.respond(
                                        HttpStatusCode.BadRequest,
                                        UserApiConst.FAIL_REGISTERED_ERROR
                                    )
                                }
                            }
                        }
                    }
                } catch (e: Throwable) {
                    application.log.error(UserApiConst.FAIL_REGISTERED_ERROR, e)
                    call.respond(
                        HttpStatusCode.BadRequest,
                        e.message.toString()
                    )
                }
            }
        }
    }
}