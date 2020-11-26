package com.yt8492.blog.server.adapter.json

import kotlinx.serialization.Serializable

@Serializable
data class EditEntryRequestJson(
    val id: String? = null,
    val title: String? = null,
    val content: String? = null,
    val tags: List<String>? = null,
    val isPreview: Boolean? = null
) : Json
