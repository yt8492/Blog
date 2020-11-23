package com.yt8492.blog.server.adapter.db.converter

import com.yt8492.blog.common.model.*
import com.yt8492.blog.server.adapter.db.dao.EntryDao
import com.yt8492.blog.server.adapter.db.dao.UserDao
import com.yt8492.blog.server.util.toDateTime

fun EntryDao.toModel(): Entry {
    return Entry(
        EntryId(id.value),
        title,
        content,
        tags.map { it.name },
        isPreview,
        createdAt.toDateTime(),
        updatedAt.toDateTime()
    )
}

fun UserDao.toModel(): User {
    return User(
        UserId(id.value),
        role,
        Password.Hashed(password)
    )
}
