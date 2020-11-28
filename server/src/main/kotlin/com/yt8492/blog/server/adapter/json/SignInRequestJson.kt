package com.yt8492.blog.server.adapter.json

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestJson(
    val id: String,
    val password: String
) : Json
