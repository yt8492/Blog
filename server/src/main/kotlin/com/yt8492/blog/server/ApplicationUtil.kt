package com.yt8492.blog.server

import com.yt8492.blog.common.json.MessageJson
import com.yt8492.blog.server.adapter.controller.Result
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*

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

suspend fun PipelineContext<Unit, ApplicationCall>.getIntQueryParameterOrRespondBadRequest(
    name: String
): Int? {
    val result = call.request.queryParameters[name]?.toIntOrNull()
    if (result == null) {
        call.respond(
            HttpStatusCode.BadRequest,
            MessageJson("query parameter $name (type: integer) is required")
        )
        return null
    }
    return result
}

suspend fun PipelineContext<Unit, ApplicationCall>.getStringPathParameterOrRespondBadRequest(
    name: String
): String? {
    val result = call.parameters[name]
    if (result == null) {
        call.respond(
            HttpStatusCode.BadRequest,
            MessageJson("path parameter $name (type: string) is required")
        )
        return null
    }
    return result
}

