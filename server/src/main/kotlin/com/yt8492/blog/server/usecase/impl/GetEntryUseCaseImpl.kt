package com.yt8492.blog.server.usecase.impl

import com.yt8492.blog.server.domain.repository.EntryRepository
import com.yt8492.blog.server.usecase.GetEntryUseCase

class GetEntryUseCaseImpl(
    private val entryRepository: EntryRepository
) : GetEntryUseCase {
    override suspend fun execute(input: GetEntryUseCase.Arg): GetEntryUseCase.Result {
        val entry = entryRepository.findById(input.id)
        return if (entry != null) {
            GetEntryUseCase.Result.Success(entry)
        } else {
            GetEntryUseCase.Result.Failure.EntryNotFound
        }
    }
}