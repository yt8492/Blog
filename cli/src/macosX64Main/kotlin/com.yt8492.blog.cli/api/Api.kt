package com.yt8492.blog.cli.api

import com.yt8492.blog.common.json.*
import com.yt8492.blog.common.json.converter.toModel
import com.yt8492.blog.common.model.*
import io.ktor.client.*
import io.ktor.client.engine.curl.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class Api(isLocal: Boolean) {

    private val baseUrl = if (isLocal) {
        "http://localhost:8080"
    } else {
        "https://blog.yt8492.com"
    }

    private val client = HttpClient(Curl) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
        defaultRequest {
            header("content-type", "application/json")
        }
    }

    suspend fun login(
        userId: UserId,
        password: Password.Raw
    ): AuthToken {
        return client.post<AuthResponseJson>("$baseUrl/api/login") {
            body = SignInRequestJson(userId.value, password.value)
        }.toModel()
    }

    suspend fun createEntry(
        token: AuthToken,
        id: EntryId?,
        title: String,
        content: String,
        tags: List<String>,
        isPreview: Boolean
    ): Entry {
        return client.post<EntryResponseJson>("$baseUrl/api/entries") {
            header("Authorization", "Bearer ${token.value}")
            body = CreateEntryRequestJson(
                id?.value,
                title,
                content,
                tags,
                isPreview
            )
        }.toModel()
    }

    suspend fun editEntry(
        token: AuthToken,
        id: EntryId,
        newId: EntryId?,
        title: String?,
        content: String?,
        tags: List<String>?,
        isPreview: Boolean?
    ): Entry {
        return client.patch<EntryResponseJson>("$baseUrl/api/entries/${id.value}") {
            header("Authorization", "Bearer ${token.value}")
            body = EditEntryRequestJson(
                newId?.value,
                title,
                content,
                tags,
                isPreview
            )
        }.toModel()
    }

    suspend fun deleteEntry(
        token: AuthToken,
        id: EntryId
    ) {
        client.delete<HttpResponse>("$baseUrl/api/entries/${id.value}") {
            header("Authorization", "Bearer ${token.value}")
        }
    }
}