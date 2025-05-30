package com.yt8492.blog.server.usecase.impl

import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.server.domain.repository.EntryRepository
import com.yt8492.blog.server.usecase.EditEntryUseCase
import korlibs.time.DateTime

class EditEntryUseCaseImpl(
    private val entryRepository: EntryRepository
) : EditEntryUseCase {
    override suspend fun execute(input: EditEntryUseCase.Arg): EditEntryUseCase.Result {
        if (input.newId != null) {
            val entry = entryRepository.findById(input.newId)
            if (entry != null) {
                return EditEntryUseCase.Result.Failure.EntryIdDuplicated
            }
        }
        val entry = entryRepository.findById(input.id)
            ?: return EditEntryUseCase.Result.Failure.EntryNotFound
        val id = input.newId ?: entry.id
        val title = input.title ?: entry.title
        val content = input.content ?: entry.content
        val tags = input.tags ?: entry.tags
        val isPreview = input.isPreview ?: entry.isPreview
        val editedEntry = Entry(
            id = id,
            title = title,
            content = content,
            tags = tags,
            isPreview = isPreview,
            createdAt = entry.createdAt,
            updatedAt = DateTime.now()
        )
        entryRepository.update(editedEntry)
        return EditEntryUseCase.Result.Success(editedEntry)
    }
}