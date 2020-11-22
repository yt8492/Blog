package com.yt8492.blog.server.usecase.impl

import com.yt8492.blog.server.domain.service.AuthService
import com.yt8492.blog.server.usecase.SignInUseCase

class SignInUseCaseImpl(
    private val authService: AuthService
) : SignInUseCase {
    override suspend fun execute(input: SignInUseCase.Arg): SignInUseCase.Result {
        val authToken = authService.signIn(input.id, input.password)
        return if (authToken != null) {
            SignInUseCase.Result.Success(authToken)
        } else {
            SignInUseCase.Result.Failure
        }
    }
}