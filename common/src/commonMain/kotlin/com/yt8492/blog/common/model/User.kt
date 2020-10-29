package com.yt8492.blog.common.model

data class User(
    val id: UserId,
    val role: Role,
    val hash: String
)
