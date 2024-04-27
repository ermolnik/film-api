package com.ermolnik.api.profiles

import com.ermolnik.repository.CreateProfileParams
import com.ermolnik.repository.ListProfilesParams
import com.ermolnik.repository.ProfileRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.profilesApi() {
    val profileRepository = ProfileRepository()

    createProfile(profileRepository)
    getProfile(profileRepository)
    listProfiles(profileRepository)
    deleteProfiles(profileRepository)
}

private fun Route.createProfile(profileRepository: ProfileRepository) {
    post("/profiles") {
        val req = call.receive<CreateProfileRequest>()

        val arg = CreateProfileParams(
            owner = req.owner,
        )

        val profile = profileRepository.create(arg)

        val resp = CreateProfileResponse(
            owner = profile,
        )
        call.respond(HttpStatusCode.Created, resp)
    }
}

private fun Route.getProfile(profileRepository: ProfileRepository) {
    get("/profiles/{id}") {
        val id = call.parameters
            .getOrFail("id")
            .toInt()

        val profile = profileRepository.get(id)

        if (profile == null) {
            // TODO: Extract error message to errorResponse function.
            call.respond(HttpStatusCode.NotFound, "profile with id $id was not found")
            return@get
        }

        call.respond(HttpStatusCode.OK, profile)
    }
}

private fun Route.listProfiles(profileRepository: ProfileRepository) {
    get("/profiles") {
        val req = with(call.request.queryParameters) {
            val owner = getOrFail("owner")
            val pageID = getOrFail("page_id")
                .toInt()
            val pageSize = getOrFail("page_size")
                .toInt()

            ListProfileRequest(
                owner = owner,
                pageID = pageID,
                pageSize = pageSize,
            )
        }

        val arg = ListProfilesParams(
            owner = req.owner,
            limit = req.pageSize,
            offset = (req.pageID - 1) * req.pageSize,
        )

        val profiles = profileRepository.list(arg)

        call.respond(HttpStatusCode.OK, profiles)
    }
}

private fun Route.deleteProfiles(profileRepository: ProfileRepository) {
    delete("/profiles/{id}") {
        val id = call.parameters
            .getOrFail("id")
            .toInt()

        profileRepository.delete(id)

        call.respond(HttpStatusCode.OK)
    }
}