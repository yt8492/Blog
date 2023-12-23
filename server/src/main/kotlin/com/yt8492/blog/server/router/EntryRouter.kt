package com.yt8492.blog.server.router

import com.yt8492.blog.server.adapter.controller.EntryController
import com.yt8492.blog.common.json.CreateEntryRequestJson
import com.yt8492.blog.common.json.EditEntryRequestJson
import com.yt8492.blog.server.getIntQueryParameterOrRespondBadRequest
import com.yt8492.blog.server.getStringPathParameterOrRespondBadRequest
import com.yt8492.blog.server.respondResult
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.routing.*

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
