package com.yt8492.blog.server

import com.yt8492.blog.server.adapter.controller.UserController
import com.yt8492.blog.server.adapter.json.SignInRequestJson
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
