package com.yt8492.blog.server.usecase.impl

import com.yt8492.blog.server.domain.repository.EntryRepository
import com.yt8492.blog.server.usecase.GetEntriesUseCase

class GetEntriesUseCaseImpl(
    private val entryRepository: EntryRepository
) : GetEntriesUseCase {
    override suspend fun execute(input: GetEntriesUseCase.Arg): GetEntriesUseCase.Result {
        val entries = entryRepository.findAllPublic(input.page)
        return GetEntriesUseCase.Result(entries)
    }
}