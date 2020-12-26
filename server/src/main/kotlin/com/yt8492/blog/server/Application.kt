package com.yt8492.blog.server

import com.yt8492.blog.common.model.UserId
import com.yt8492.blog.server.adapter.controller.EntryController
import com.yt8492.blog.server.adapter.controller.UserController
import com.yt8492.blog.server.adapter.db.DBHelper
import com.yt8492.blog.server.adapter.jwt.UserJWTService
import com.yt8492.blog.server.di.adapterModule
import com.yt8492.blog.server.di.domainModule
import com.yt8492.blog.server.di.mainModule
import com.yt8492.blog.server.di.useCaseModule
import com.yt8492.blog.server.domain.repository.EntryRepository
import com.yt8492.blog.server.domain.repository.UserRepository
import com.yt8492.blog.server.router.entryRouter
import com.yt8492.blog.server.router.userRouter
import com.yt8492.blog.server.router.viewRouter
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.features.*
import org.slf4j.event.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.serialization.*
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(ContentNegotiation) {
        json()
    }

    install(Koin) {
        modules(domainModule, useCaseModule, adapterModule, mainModule)
    }

    install(CORS) {
        anyHost()
        method(HttpMethod.Post)
        allowSameOrigin = false
        allowNonSimpleContentTypes = true
    }

    install(StatusPages) {
        exception<Throwable> {
            log.error("unknown error", it)
            call.respond(HttpStatusCode.InternalServerError)
        }
    }

    val userJWTService: UserJWTService by inject()
    val userRepository: UserRepository by inject()

    install(Authentication) {
        jwt {
            realm = AppConfig.domain
            verifier(userJWTService.createVerifier())
            validate { credential ->
                val userId = credential.payload.getClaim(UserJWTService.USER_ID_CLAIM).asString().let(::UserId)
                userRepository.findById(userId)?.let {
                    UserIdPrincipal(it.id.value)
                }
            }
        }
    }

    val dbHelper: DBHelper by inject()
    dbHelper.initDB()

    val entryController: EntryController by inject()
    val userController: UserController by inject()
    val entryRepository: EntryRepository by inject()

    routing {
        route("/api") {
            entryRouter(entryController)
            userRouter(userController)
        }
        viewRouter(entryRepository)
    }
}
