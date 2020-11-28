package com.yt8492.blog.server

import com.yt8492.blog.server.adapter.controller.Result
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

suspend fun ApplicationCall.respondResult(result: Result) {
    val statusCode = HttpStatusCode.fromValue(result.statusCode)
    when (result) {
        is Result.Object -> {
            respond(statusCode, result.json)
        }
        is Result.Array -> {
            respond(statusCode, result.list)
        }
    }

}
