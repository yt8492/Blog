package com.yt8492.blog.server.adapter.controller.converter

import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.server.adapter.controller.Result
import com.yt8492.blog.common.json.AuthResponseJson
import com.yt8492.blog.common.json.Empty
import com.yt8492.blog.common.json.EntryResponseJson
import com.yt8492.blog.common.json.MessageJson
import com.yt8492.blog.server.usecase.*
import korlibs.time.ISO8601

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

fun SignInUseCase.Result.toControllerResult(): Result {
    return when (this) {
        is SignInUseCase.Result.Success ->
            Result(
                AuthResponseJson(token.value),
                200
            )
        is SignInUseCase.Result.Failure ->
            Result(
                MessageJson("Login failed"),
                401
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
