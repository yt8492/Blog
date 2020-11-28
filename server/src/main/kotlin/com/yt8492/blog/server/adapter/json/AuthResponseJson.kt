package com.yt8492.blog.server.adapter.json

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseJson(
    val token: String
) : Json
