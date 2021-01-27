package com.yt8492.blog.server.router

import com.yt8492.blog.common.Constants
import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId
import com.yt8492.blog.server.OGPService
import com.yt8492.blog.server.domain.repository.EntryRepository
import com.yt8492.blog.server.getStringPathParameterOrRespondBadRequest
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
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
            file("favicon.ico", "generated/favicon.ico")
            resource("TopOGP.png")
        }
    }
    route("/entries") {
        get {
            respondEntries(call)
        }
        static {
            files("generated")
            file("main.js", "generated/main.js")
            file("favicon.ico", "generated/favicon.ico")
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
        get("/ogp") {
            val entryId = getStringPathParameterOrRespondBadRequest("id") ?: return@get
            val entry = entryRepository.findById(EntryId(entryId)) ?: return@get
            val bytes = OGPService.createImageByteArray(entry)
            call.response.headers.append(HttpHeaders.CacheControl, "max-age=3600")
            call.respondBytes(bytes, ContentType.defaultForFileExtension("png"))
        }
    }
}

private suspend fun respondEntries(call: ApplicationCall) {
    call.respondHtml {
        lang = "ja"
        head {
            meta(charset = "UTF-8")
            title(Constants.BLOG_TITLE)
            link(rel = "icon", type = "image/vnd.microsoft.icon", href = "favicon.ico")
            meta("keywords", "yt8492,マヤミト,ブログ")
            meta(property = "og:title", content = """Log.d("yt8492", blog)""")
            meta(property = "og:type", content = "article")
            meta(property = "og:description", content = "マヤミトのブログ")
            meta(property = "og:url", content = "https://blog.yt8492.com")
            meta(property = "og:site_name", content = Constants.BLOG_TITLE)
            meta(property = "og:image", content = "https://blog.yt8492.com/TopOGP.png")
            meta(name = "twitter:card", content = "summary")
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
            link(rel = "icon", type = "image/vnd.microsoft.icon", href = "favicon.ico")
            if (entry != null) {
                val keywords = listOf(
                    "yt8492",
                    "マヤミト",
                    "ブログ"
                ) + entry.tags
                meta(name = "keywords", keywords.joinToString(","))
                meta(name = "description", entry.title)
                title("${entry.title} - ${Constants.BLOG_TITLE}")
                meta(property = "og:title", content = entry.title)
                meta(property = "og:type", content = "article")
                meta(property = "og:description", content = entry.content.take(140))
                meta(property = "og:url", content = "https://blog.yt8492.com/entries/${entry.id.value}")
                meta(property = "og:site_name", content = Constants.BLOG_TITLE)
                meta(property = "og:image", content = "https://blog.yt8492.com/entries/${entry.id.value}/ogp")
                meta(name = "twitter:card", content = "summary_large_image")
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

@HtmlTagMarker
private fun FlowOrPhrasingOrMetaDataContent .meta(property: String, content: String) {
    meta {
        attributes["property"] = property
        this.content = content
    }
}
