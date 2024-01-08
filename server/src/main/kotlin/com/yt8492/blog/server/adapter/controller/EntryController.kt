package com.yt8492.blog.server.adapter.controller

import com.yt8492.blog.common.json.*
import com.yt8492.blog.common.model.EntryId
import com.yt8492.blog.server.adapter.controller.converter.toControllerResult
import com.yt8492.blog.server.usecase.*

class EntryController(
    private val createEntryUseCase: CreateEntryUseCase,
    private val editEntryUseCase: EditEntryUseCase,
    private val getEntryUseCase: GetEntryUseCase,
    private val getEntriesUseCase: GetEntriesUseCase,
    private val deleteEntryUseCase: DeleteEntryUseCase
) {
    suspend fun getEntry(id: String): Result<MessageJson, EntryResponseJson> {
        val arg = GetEntryUseCase.Arg(EntryId(id))
        val result = getEntryUseCase.execute(arg)
        return result.toControllerResult()
    }

    suspend fun getEntries(page: Int): Result<Nothing, List<EntryResponseJson>> {
        val arg = GetEntriesUseCase.Arg(page)
        val result = getEntriesUseCase.execute(arg)
        return result.toControllerResult()
    }

    suspend fun createEntry(entry: CreateEntryRequestJson): Result<MessageJson, EntryResponseJson> {
        val arg = CreateEntryUseCase.Arg(
            entry.id?.let(::EntryId),
            entry.title,
            entry.content,
            entry.tags,
            entry.isPreview
        )
        val result = createEntryUseCase.execute(arg)
        return result.toControllerResult()
    }

    suspend fun editEntry(id: String, entry: EditEntryRequestJson): Result<MessageJson, EntryResponseJson> {
        val arg = EditEntryUseCase.Arg(
            EntryId(id),
            entry.id?.let(::EntryId),
            entry.title,
            entry.content,
            entry.tags,
            entry.isPreview
        )
        val result = editEntryUseCase.execute(arg)
        return result.toControllerResult()
    }

    suspend fun deleteEntry(id: String): Result<MessageJson, Empty> {
        val arg = DeleteEntryUseCase.Arg(
            EntryId(id)
        )
        val result = deleteEntryUseCase.execute(arg)
        return result.toControllerResult()
    }
}
