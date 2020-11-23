package com.yt8492.blog.server

import com.typesafe.config.ConfigFactory
import io.ktor.config.*
import io.ktor.util.*

@OptIn(KtorExperimentalAPI::class)
object AppConfig {
    private val config = HoconApplicationConfig(ConfigFactory.load())

    val dbDriver: String = config.property("ktor.db.driver").getString()
    val dbUrl: String = config.property("ktor.db.jdbcUrl").getString()
    val dbUser: String = config.property("ktor.db.dbUser").getString()
    val dbPassword: String = config.property("ktor.db.dbPassword").getString()

    val authSecret: String = config.property("ktor.auth.secret").getString()
    val authSalt: String = config.property("ktor.auth.salt").getString()

    val domain: String = config.property("ktor.domain").getString()
}