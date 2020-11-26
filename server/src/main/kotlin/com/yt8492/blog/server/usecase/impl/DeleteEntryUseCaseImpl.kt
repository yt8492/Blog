package com.yt8492.blog.server.usecase.impl

import com.yt8492.blog.server.domain.repository.EntryRepository
import com.yt8492.blog.server.usecase.DeleteEntryUseCase

class DeleteEntryUseCaseImpl(
    private val entryRepository: EntryRepository
) : DeleteEntryUseCase {
    override suspend fun execute(input: DeleteEntryUseCase.Arg): DeleteEntryUseCase.Result {
        val success = entryRepository.delete(input.id)
        return if (success) {
            DeleteEntryUseCase.Result.Success
        } else {
            DeleteEntryUseCase.Result.Failure.EntryNotFound
        }
    }
}