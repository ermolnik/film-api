package com.ermolnik.repository

import com.ermolnik.db.User
import com.ermolnik.db.Users
import com.ermolnik.db.dbQuery
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class CreateUserParams(
    val username: String,
    val hashedPassword: String,
    val fullName: String,
    val email: String,
)

class UserRepository {
    suspend fun create(arg: CreateUserParams): String = dbQuery {
        Users.insert {
            it[username] = arg.username
            it[hashedPassword] = arg.hashedPassword
            it[fullName] = arg.fullName
            it[email] = arg.email
        } get Users.username
    }

    suspend fun get(username: String): User? = dbQuery {
        Users.selectAll()
            .where {
                Users.username eq username
            }
            .map {
                User(
                    username = it[Users.username],
                    hashedPassword = it[Users.hashedPassword],
                    fullName = it[Users.fullName],
                    email = it[Users.email],
                    passwordChangedAt = it[Users.passwordChangedAt],
                    createdAt = it[Users.createdAt],
                )
            }
            .singleOrNull()
    }
}