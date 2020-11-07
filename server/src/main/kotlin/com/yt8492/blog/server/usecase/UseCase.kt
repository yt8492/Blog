package com.yt8492.blog.server.usecase

interface UseCase<Arg, Result> {
    suspend fun execute(input: Arg): Result
}
