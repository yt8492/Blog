package com.yt8492.blog.server.adapter.json

import kotlinx.serialization.Serializable

@Serializable
data class EntryResponseJson(
    val id: String,
    val title: String,
    val content: String,
    val tags: List<String>,
    val isPreview: Boolean,
    val createdAt: String,
    val updatedAt: String
) : Json
