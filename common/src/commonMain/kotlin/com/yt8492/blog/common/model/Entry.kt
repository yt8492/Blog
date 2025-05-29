package com.yt8492.blog.common.model

import korlibs.time.DateTime

data class Entry(
    val id: EntryId,
    val title: String,
    val content: String,
    val tags: List<String>,
    val isPreview: Boolean,
    val createdAt: DateTime,
    val updatedAt: DateTime
)
