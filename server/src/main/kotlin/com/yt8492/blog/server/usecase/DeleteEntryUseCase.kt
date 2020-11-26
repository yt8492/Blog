package com.yt8492.blog.server.usecase

import com.yt8492.blog.common.model.EntryId

interface DeleteEntryUseCase : UseCase<DeleteEntryUseCase.Arg, DeleteEntryUseCase.Result> {
    data class Arg(
        val id: EntryId
    )

    sealed class Result {
        object Success : Result()
        sealed class Failure : Result() {
            object EntryNotFound : Failure()
        }
    }
}
