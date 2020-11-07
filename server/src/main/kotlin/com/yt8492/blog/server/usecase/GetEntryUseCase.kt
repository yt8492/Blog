package com.yt8492.blog.server.usecase

import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId

interface GetEntryUseCase : UseCase<GetEntryUseCase.Arg, GetEntryUseCase.Result> {
    data class Arg(
        val id: EntryId
    )

    data class Result(
        val entry: Entry
    )
}
