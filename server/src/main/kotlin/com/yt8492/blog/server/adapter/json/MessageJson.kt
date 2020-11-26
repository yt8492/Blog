package com.yt8492.blog.server.adapter.json

import kotlinx.serialization.Serializable

@Serializable
data class MessageJson(val message: String) : Json
