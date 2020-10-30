package com.yt8492.blog.common.model

import com.soywiz.klock.DateTime

data class Entry(
    val title: String,
    val content: String,
    val tags: List<String>,
    val isPreview: Boolean,
    val createdAt: DateTime,
    val updatedAt: DateTime
)
