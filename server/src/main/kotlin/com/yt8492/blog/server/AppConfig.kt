package com.yt8492.blog.server

import com.typesafe.config.ConfigFactory
import io.ktor.config.*
import io.ktor.util.*

@OptIn(KtorExperimentalAPI::class)
object AppConfig {
    private val config = HoconApplicationConfig(ConfigFactory.load())

    val authSecret: String = config.property("ktor.auth.secret").getString()

    val domain: String = config.property("ktor.domain").getString()
}
