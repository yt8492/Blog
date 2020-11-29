package com.yt8492.blog.server.adapter.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EntryResponseJson(
    val id: String,
    val title: String,
    val content: String,
    val tags: List<String>,
    @SerialName("is_preview")
    val isPreview: Boolean,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String
) : Json
