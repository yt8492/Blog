package com.yt8492.blog.server.adapter.controller.converter

import com.soywiz.klock.ISO8601
import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.server.adapter.controller.Result
import com.yt8492.blog.server.adapter.json.Empty
import com.yt8492.blog.server.adapter.json.EntryResponseJson
import com.yt8492.blog.server.adapter.json.MessageJson
import com.yt8492.blog.server.usecase.*

fun GetEntryUseCase.Result.toControllerResult(): Result {
    return when (this) {
        is GetEntryUseCase.Result.Success ->
            Result(
                entry.toJson(),
                200
            )
        is GetEntryUseCase.Result.Failure ->
            Result(
                MessageJson("Entry not found"),
                404
            )
    }
}

fun GetEntriesUseCase.Result.toControllerResult(): Result {
    return Result(
       entries.map { it.toJson() },
        200
    )
}

fun CreateEntryUseCase.Result.toControllerResult(): Result {
    return when (this) {
        is CreateEntryUseCase.Result.Success ->
            Result(
                entry.toJson(),
                201
            )
        is CreateEntryUseCase.Result.Failure.EntryIdDuplicated ->
            Result(
                MessageJson("Entry id duplicated"),
                409
            )
    }
}

fun EditEntryUseCase.Result.toControllerResult(): Result {
    return when (this) {
        is EditEntryUseCase.Result.Success ->
            Result(
                entry.toJson(),
                200
            )
        is EditEntryUseCase.Result.Failure.EntryNotFound ->
            Result(
                MessageJson("Entry not found"),
                404
            )
        is EditEntryUseCase.Result.Failure.EntryIdDuplicated ->
            Result(
                MessageJson("Entry id duplicated"),
                409
            )
    }
}

fun DeleteEntryUseCase.Result.toControllerResult(): Result {
    return when (this) {
        is DeleteEntryUseCase.Result.Success ->
            Result(
                Empty,
                204
            )
        is DeleteEntryUseCase.Result.Failure.EntryNotFound ->
            Result(
                MessageJson("Entry not found"),
                404
            )
    }
}

private fun Entry.toJson(): EntryResponseJson {
    return EntryResponseJson(
        id.value,
        title,
        content,
        tags,
        isPreview,
        createdAt.format(ISO8601.DATETIME_UTC_COMPLETE),
        updatedAt.format(ISO8601.DATETIME_UTC_COMPLETE)
    )
}
