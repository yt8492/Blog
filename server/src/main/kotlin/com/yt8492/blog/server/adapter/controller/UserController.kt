package com.yt8492.blog.server.adapter.controller

import com.yt8492.blog.common.model.Password
import com.yt8492.blog.common.model.UserId
import com.yt8492.blog.server.adapter.controller.converter.toControllerResult
import com.yt8492.blog.server.adapter.json.SignInRequestJson
import com.yt8492.blog.server.usecase.SignInUseCase

class UserController(
    private val signInUseCase: SignInUseCase
) {
    suspend fun signIn(signInRequestJson: SignInRequestJson): Result {
        val arg = SignInUseCase.Arg(
            UserId(signInRequestJson.id),
            Password.Raw(signInRequestJson.password)
        )
        val result = signInUseCase.execute(arg)
        return result.toControllerResult()
    }
}
