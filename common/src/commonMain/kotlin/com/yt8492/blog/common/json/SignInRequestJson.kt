package com.yt8492.blog.common.json

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestJson(
    val id: String,
    val password: String
) : Json
