package com.yt8492.blog.server

import com.yt8492.blog.server.di.adapterModule
import com.yt8492.blog.server.di.domainModule
import com.yt8492.blog.server.di.useCaseModule
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.features.*
import org.slf4j.event.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import io.ktor.serialization.*
import org.koin.ktor.ext.Koin

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(Authentication) {
    }

    install(ContentNegotiation) {
        json()
    }

    install(Koin) {
        modules(domainModule, useCaseModule, adapterModule)
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }
    }
}

