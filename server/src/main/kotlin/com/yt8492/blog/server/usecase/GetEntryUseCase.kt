package com.yt8492.blog.server.usecase

import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId

interface GetEntryUseCase : UseCase<GetEntryUseCase.Arg, GetEntryUseCase.Result> {
    data class Arg(
        val id: EntryId
    )

    sealed class Result {
        data class Success(val entry: Entry) : Result()
        sealed class Failure : Result() {
            object EntryNotFound : Failure()
        }
    }
}
