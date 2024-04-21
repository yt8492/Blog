package api

import com.yt8492.blog.common.json.EntryResponseJson
import com.yt8492.blog.common.json.converter.toModel
import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

object Api {

    private val baseUrl = js("BLOG_BASE_URL") as String

    private val client = HttpClient(Js) {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun getPublicEntries(page: Int): List<Entry> {
        return client.get("$baseUrl/api/entries?page=$page")
            .body<List<EntryResponseJson>>()
            .map { it.toModel() }
    }

    suspend fun getEntryById(id: EntryId): Entry? {
        return client.get("$baseUrl/api/entries/${id.value}")
            .body<EntryResponseJson>()
            .toModel()
    }
}
