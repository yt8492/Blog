package com.yt8492.blog.server.router

import com.yt8492.blog.server.adapter.controller.UserController
import com.yt8492.blog.common.json.SignInRequestJson
import com.yt8492.blog.server.respondResult
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*

fun Route.userRouter(controller: UserController) {
    route("/login") {
        post {
            val body = call.receive<SignInRequestJson>()
            val result = controller.signIn(body)
            call.respondResult(result)
        }
    }
}
