package com.yt8492.blog.server.usecase

import com.yt8492.blog.common.model.Entry

interface GetEntriesUseCase : UseCase<GetEntriesUseCase.Arg, GetEntriesUseCase.Result> {
    data class Arg(
        val page: Int
    )

    data class Result(
        val entries: List<Entry>
    )
}
