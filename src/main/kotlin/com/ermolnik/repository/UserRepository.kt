package com.ermolnik.repository

import com.ermolnik.db.User
import com.ermolnik.db.Users
import com.ermolnik.db.dbQuery
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

data class CreateUserParams(
    val username: String,
    val hashedPassword: String,
    val email: String,
    val token: String
)

class UserRepository {
    suspend fun create(arg: CreateUserParams): Result = dbQuery {
        when (val result = Users.insert {
            it[username] = arg.username
            it[hashedPassword] = arg.hashedPassword
            it[email] = arg.email
        }.getOrNull(Users.username)) {
            null -> Result.Error
            else -> Result.Success.Created(userName = result)
        }
    }

    suspend fun findUser(username: String): Result = dbQuery {
        try {
            when (val result = Users.selectAll()
                .where {
                    Users.username eq username
                }
                .mapNotNull {
                    User(
                        userName = it[Users.username],
                        hashedPassword = it[Users.hashedPassword],
                        email = it[Users.email],
                        passwordChangedAt = it[Users.passwordChangedAt],
                        createdAt = it[Users.createdAt],
                    )
                }
                .singleOrNull()) {
                is User -> Result.Success.Found(result)
                else -> Result.Error
            }
        } catch (e: Throwable) {
            Result.Error
        }
    }

    sealed interface Result {
        data object Error : Result
        sealed interface Success : Result {
            data class Found(
                val user: User
            ) : Success

            data class Created(
                val userName: String
            ) : Success
        }
    }
}