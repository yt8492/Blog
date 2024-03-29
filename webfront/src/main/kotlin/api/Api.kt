package api

import com.yt8492.blog.common.json.EntryResponseJson
import com.yt8492.blog.common.json.converter.toModel
import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

object Api {

    private val baseUrl = js("BLOG_BASE_URL") as String

    private val client = HttpClient(Js) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    suspend fun getPublicEntries(page: Int): List<Entry> {
        return client.get<List<EntryResponseJson>>("$baseUrl/api/entries?page=$page")
            .map { it.toModel() }
    }

    suspend fun getEntryById(id: EntryId): Entry? {
        return client.get<EntryResponseJson>("$baseUrl/api/entries/${id.value}")
            .toModel()
    }
}
