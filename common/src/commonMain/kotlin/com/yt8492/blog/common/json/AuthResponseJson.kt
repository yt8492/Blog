package com.yt8492.blog.common.json

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseJson(
    val token: String
)
