package com.yt8492.blog.server.usecase.impl

import com.soywiz.klock.DateTime
import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId
import com.yt8492.blog.server.domain.repository.EntryRepository
import com.yt8492.blog.server.usecase.CreateEntryUseCase
import java.util.UUID

class CreateEntryUseCaseImpl(
    private val entryRepository: EntryRepository
) : CreateEntryUseCase {
    override suspend fun execute(input: CreateEntryUseCase.Arg): CreateEntryUseCase.Result {
        input.id?.let { id ->
            val existingEntry = entryRepository.findById(id)
            if (existingEntry != null) {
                return CreateEntryUseCase.Result.Failure.EntryIdDuplicated
            }
        }
        val id = input.id ?: EntryId(UUID.randomUUID().toString())
        val now = DateTime.now()
        val entry = Entry(
            id,
            input.title,
            input.content,
            input.tags,
            input.isPreview,
            now,
            now
        )
        entryRepository.create(entry)
        return CreateEntryUseCase.Result.Success(entry)
    }
}