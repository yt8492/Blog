package com.yt8492.blog.server.router

import com.yt8492.blog.server.adapter.controller.EntryController
import com.yt8492.blog.common.json.CreateEntryRequestJson
import com.yt8492.blog.common.json.EditEntryRequestJson
import com.yt8492.blog.server.getIntQueryParameterOrRespondBadRequest
import com.yt8492.blog.server.getStringPathParameterOrRespondBadRequest
import com.yt8492.blog.server.respondResult
import io.ktor.application.*
import io.ktor.auth.*
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

        authenticate {
            post {
                val body = call.receive<CreateEntryRequestJson>()
                val result = controller.createEntry(body)
                call.respondResult(result)
            }
        }

        route("/{id}") {
            get {
                val id = getStringPathParameterOrRespondBadRequest("id") ?: return@get
                val result = controller.getEntry(id)
                call.respondResult(result)
            }

            authenticate {
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
}
