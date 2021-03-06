package com.yt8492.blog.server.usecase

import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId

interface CreateEntryUseCase : UseCase<CreateEntryUseCase.Arg, CreateEntryUseCase.Result> {
    data class Arg(
        val id: EntryId?,
        val title: String,
        val content: String,
        val tags: List<String>,
        val isPreview: Boolean
    )

    sealed class Result {
        data class Success(val entry: Entry) : Result()
        sealed class Failure : Result() {
            object EntryIdDuplicated : Failure()
        }
    }
}
