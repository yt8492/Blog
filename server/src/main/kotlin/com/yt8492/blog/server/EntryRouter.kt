package com.yt8492.blog.server

import com.yt8492.blog.server.adapter.controller.EntryController
import com.yt8492.blog.server.adapter.json.CreateEntryRequestJson
import com.yt8492.blog.server.adapter.json.EditEntryRequestJson
import com.yt8492.blog.server.adapter.json.MessageJson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.pipeline.*

fun Route.entryRouter(controller: EntryController) {
    route("/entries") {
        get {
            val page = getIntQueryParameterOrRespondBadRequest("page") ?: return@get
            val result = controller.getEntries(page)
            call.respondResult(result)
        }

        post {
            val body = call.receive<CreateEntryRequestJson>()
            val result = controller.createEntry(body)
            call.respondResult(result)
        }

        route("/{id}") {
            get {
                val id = getStringPathParameterOrRespondBadRequest("id") ?: return@get
                val result = controller.getEntry(id)
                call.respondResult(result)
            }

            patch {
                val id = getStringPathParameterOrRespondBadRequest("id") ?: return@patch
                val body = call.receive<EditEntryRequestJson>()
                val result = controller.editEntry(id, body)
                call.respondResult(result)
            }

            delete {
                val id = getStringPathParameterOrRespondBadRequest("id") ?: return@delete
                val result = controller.deleteEntry(id)
                call.respondResult(result)
            }
        }
    }
}

private suspend fun PipelineContext<Unit, ApplicationCall>.getIntQueryParameterOrRespondBadRequest(
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

private suspend fun PipelineContext<Unit, ApplicationCall>.getStringPathParameterOrRespondBadRequest(
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