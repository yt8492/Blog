package com.yt8492.blog.cli.api

import com.yt8492.blog.common.json.*
import com.yt8492.blog.common.json.converter.toModel
import com.yt8492.blog.common.model.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.curl.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class Api(isLocal: Boolean) {

    private val baseUrl = if (isLocal) {
        "http://localhost:8080"
    } else {
        "https://blog.yt8492.com"
    }

    private val client = HttpClient(Curl) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        defaultRequest {
            header("content-type", "application/json")
        }
    }

    suspend fun login(
        userId: UserId,
        password: Password.Raw
    ): AuthToken {
        return client.post("$baseUrl/api/login") {
            setBody(SignInRequestJson(userId.value, password.value))
        }.body<AuthResponseJson>().toModel()
    }

    suspend fun createEntry(
        token: AuthToken,
        id: EntryId?,
        title: String,
        content: String,
        tags: List<String>,
        isPreview: Boolean
    ): Entry {
        return client.post("$baseUrl/api/entries") {
            header("Authorization", "Bearer ${token.value}")
            setBody(
                CreateEntryRequestJson(
                    id?.value,
                    title,
                    content,
                    tags,
                    isPreview
                )
            )
        }.body<EntryResponseJson>().toModel()
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
        return client.patch("$baseUrl/api/entries/${id.value}") {
            header("Authorization", "Bearer ${token.value}")
            setBody(
                EditEntryRequestJson(
                    newId?.value,
                    title,
                    content,
                    tags,
                    isPreview
                )
            )
        }.body<EntryResponseJson>().toModel()
    }

    suspend fun deleteEntry(
        token: AuthToken,
        id: EntryId
    ) {
        client.delete("$baseUrl/api/entries/${id.value}") {
            header("Authorization", "Bearer ${token.value}")
        }
    }
}