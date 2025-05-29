package com.yt8492.blog.server

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*

object AppConfig {
    private val config = HoconApplicationConfig(ConfigFactory.load())

    val authSecret: String = config.property("ktor.auth.secret").getString()

    val domain: String = config.property("ktor.domain").getString()
}
