package com.yt8492.blog.server.adapter.json

import kotlinx.serialization.Serializable

@Serializable
data class CreateEntryRequestJson(
    val id: String? = null,
    val title: String,
    val content: String,
    val tags: List<String>,
    val isPreview: Boolean
) : Json
