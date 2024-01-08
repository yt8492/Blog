package com.yt8492.blog.server

import com.yt8492.blog.common.model.UserId
import com.yt8492.blog.server.adapter.controller.EntryController
import com.yt8492.blog.server.adapter.controller.UserController
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
import org.slf4j.event.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@JvmOverloads
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
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Patch)
        allowSameOrigin = false
        allowNonSimpleContentTypes = true
    }

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            this@module.log.error("unknown error", cause)
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
