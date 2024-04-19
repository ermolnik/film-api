package com.ermolnik.api.accounts

import com.ermolnik.repository.AccountRepository
import com.ermolnik.repository.CreateAccountParams
import com.ermolnik.repository.ListAccountsParams
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.accountsApi() {
    val accountRepository = AccountRepository()

    // Create account
    post("/accounts") {
        val req = call.receive<CreateAccountRequest>()

        val arg = CreateAccountParams(
            owner = req.owner,
        )

        val account = accountRepository.create(arg)

        val resp = CreateAccountResponse(
            owner = account,
        )
        call.respond(HttpStatusCode.Created, resp)
    }

    // Get account
    get("/accounts/{id}") {
        val id = call.parameters
            .getOrFail("id")
            .toInt()

        val account = accountRepository.get(id)

        if (account == null) {
            // TODO: Extract error message to errorResponse function.
            call.respond(HttpStatusCode.NotFound, "account with id $id was not found")
            return@get
        }

        call.respond(HttpStatusCode.OK, account)
    }

    // List accounts
    get("/accounts") {
        val req = with(call.request.queryParameters) {
            val owner = getOrFail("owner")
            val pageID = getOrFail("page_id")
                .toInt()
            val pageSize = getOrFail("page_size")
                .toInt()

            ListAccountRequest(
                owner = owner,
                pageID = pageID,
                pageSize = pageSize,
            )
        }

        val arg = ListAccountsParams(
            owner = req.owner,
            limit = req.pageSize,
            offset = (req.pageID - 1) * req.pageSize,
        )

        val accounts = accountRepository.list(arg)

        call.respond(HttpStatusCode.OK, accounts)
    }

    // Delete account
    delete("/accounts/{id}") {
        val id = call.parameters
            .getOrFail("id")
            .toInt()

        accountRepository.delete(id)

        call.respond(HttpStatusCode.OK)
    }
}