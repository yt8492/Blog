package com.yt8492.blog.server.router

import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId
import com.yt8492.blog.server.domain.repository.EntryRepository
import com.yt8492.blog.server.getStringPathParameterOrRespondBadRequest
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.viewRouter(entryRepository: EntryRepository) {
    route("/") {
        get {
            respondEntries(call)
        }
        static {
            files("generated")
        }
    }
    route("/entries") {
        get {
            respondEntries(call)
        }
        static {
            files("generated")
            file("main.js", "generated/main.js")
            static("/logo") {
                files("generated/logo")
            }
        }
    }
    route("/entries/{id}") {
        get {
            val entryId = getStringPathParameterOrRespondBadRequest("id") ?: return@get
            val entry = entryRepository.findById(EntryId(entryId))
            respondEntry(call, entry)
        }
    }
}

private suspend fun respondEntries(call: ApplicationCall) {
    call.respondHtml {
        lang = "ja"
        head {
            meta(charset = "UTF-8")
            title(BLOG_TITLE)
            meta("keywords", "yt8492,マヤミト,ブログ")
        }
        body {
            div {
                id = "root"
            }
            script(src = "main.js", block = {})
        }
    }
}

private suspend fun respondEntry(call: ApplicationCall, entry: Entry?) {
    call.respondHtml {
        lang = "ja"
        head {
            meta(charset = "UTF-8")
            if (entry != null) {
                val keywords = listOf(
                    "yt8492",
                    "マヤミト",
                    "ブログ"
                ) + entry.tags
                meta("keywords", keywords.joinToString(","))
                meta("description", entry.title)
            }
        }
        body {
            div {
                id = "root"
            }
            script(src = "main.js", block = {})
        }
    }
}

private const val BLOG_TITLE = """Log.d("yt8492", blog)"""
