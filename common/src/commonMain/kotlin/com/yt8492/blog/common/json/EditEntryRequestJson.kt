package com.yt8492.blog.common.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditEntryRequestJson(
    val id: String? = null,
    val title: String? = null,
    val content: String? = null,
    val tags: List<String>? = null,
    @SerialName("is_preview")
    val isPreview: Boolean? = null
) : Json
