package com.ermolnik.repository

import com.ermolnik.db.Profile
import com.ermolnik.db.Profiles
import com.ermolnik.db.dbQuery
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll

class CreateProfileParams(
    val owner: String,
)

class ListProfilesParams(
    val owner: String,
    val limit: Int,
    val offset: Int,
)

class ProfileRepository {
    suspend fun create(arg: CreateProfileParams): String = dbQuery {
        Profiles.insert {
            it[owner] = arg.owner
        } get Profiles.owner
    }

    suspend fun get(id: Int): Profile? = dbQuery {
        Profiles.selectAll()
            .where {
                Profiles.id eq id
            }
            .map {
                Profile(
                    id = it[Profiles.id],
                    owner = it[Profiles.owner],
                    createdAt = it[Profiles.createdAt],
                )
            }
            .singleOrNull()
    }

    suspend fun list(arg: ListProfilesParams): List<Profile> = dbQuery {
        Profiles.selectAll()
            .where {
                Profiles.owner eq arg.owner
            }
            .orderBy(Profiles.id)
            .limit(arg.limit, offset = arg.offset.toLong())
            .map {
                Profile(
                    id = it[Profiles.id],
                    owner = it[Profiles.owner],
                    createdAt = it[Profiles.createdAt],
                )
            }
    }

    suspend fun delete(id: Int): Int = dbQuery {
        Profiles.deleteWhere {
            Profiles.id eq id
        }
    }
}