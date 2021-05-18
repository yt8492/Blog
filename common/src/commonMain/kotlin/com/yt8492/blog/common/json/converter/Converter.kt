package com.yt8492.blog.common.json.converter

import com.soywiz.klock.DateTime
import com.yt8492.blog.common.json.AuthResponseJson
import com.yt8492.blog.common.json.EntryResponseJson
import com.yt8492.blog.common.model.AuthToken
import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId

fun EntryResponseJson.toModel(): Entry {
    return Entry(
        EntryId(id),
        title,
        content,
        tags,
        isPreview,
        DateTime.fromString(createdAt).local,
        DateTime.fromString(updatedAt).local
    )
}

fun AuthResponseJson.toModel(): AuthToken {
    return AuthToken(token)
}
