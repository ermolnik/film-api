package com.ermolnik.repository

import com.ermolnik.db.Account
import com.ermolnik.db.Accounts
import com.ermolnik.db.dbQuery
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class CreateAccountParams(
    val owner: String,
)

class ListAccountsParams(
    val owner: String,
    val limit: Int,
    val offset: Int,
)

class AccountRepository {
    suspend fun create(arg: CreateAccountParams): String = dbQuery {
        Accounts.insert {
            it[owner] = arg.owner
        } get Accounts.owner
    }

    suspend fun get(id: Int): Account? = dbQuery {
        Accounts.selectAll()
            .where {
                Accounts.id eq id
            }
            .map {
                Account(
                    id = it[Accounts.id],
                    owner = it[Accounts.owner],
                    createdAt = it[Accounts.createdAt],
                )
            }
            .singleOrNull()
    }

    suspend fun list(arg: ListAccountsParams): List<Account> = dbQuery {
        Accounts.selectAll()
            .where {
                Accounts.owner eq arg.owner
            }
            .orderBy(Accounts.id)
            .limit(arg.limit, offset = arg.offset.toLong())
            .map {
                Account(
                    id = it[Accounts.id],
                    owner = it[Accounts.owner],
                    createdAt = it[Accounts.createdAt],
                )
            }
    }

    suspend fun delete(id: Int): Int = dbQuery {
        Accounts.deleteWhere {
            Accounts.id eq id
        }
    }
}