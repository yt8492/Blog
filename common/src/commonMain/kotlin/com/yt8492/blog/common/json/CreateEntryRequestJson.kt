package com.yt8492.blog.common.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateEntryRequestJson(
    val id: String? = null,
    val title: String,
    val content: String,
    val tags: List<String>,
    @SerialName("is_preview")
    val isPreview: Boolean
)
