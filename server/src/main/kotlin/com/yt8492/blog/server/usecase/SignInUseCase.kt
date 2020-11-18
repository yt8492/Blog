package com.yt8492.blog.server.usecase

import com.yt8492.blog.common.model.AuthToken
import com.yt8492.blog.common.model.Password
import com.yt8492.blog.common.model.UserId

interface SignInUseCase : UseCase<SignInUseCase.Arg, SignInUseCase.Result> {
    data class Arg(
        val id: UserId,
        val password: Password.Raw
    )

    sealed class Result {
        data class Success(val token: AuthToken) : Result()
        sealed class Failure : Result() {
            object UserIdNotFound : Failure()
            object InvalidPassword : Failure()
        }
    }
}
