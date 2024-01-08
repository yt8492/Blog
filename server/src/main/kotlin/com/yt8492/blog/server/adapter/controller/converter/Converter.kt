package com.yt8492.blog.server.adapter.controller.converter

import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.server.adapter.controller.Result
import com.yt8492.blog.common.json.AuthResponseJson
import com.yt8492.blog.common.json.Empty
import com.yt8492.blog.common.json.EntryResponseJson
import com.yt8492.blog.common.json.MessageJson
import com.yt8492.blog.server.usecase.*
import korlibs.time.ISO8601

fun GetEntryUseCase.Result.toControllerResult(): Result<MessageJson, EntryResponseJson> {
    return when (this) {
        is GetEntryUseCase.Result.Success ->
            Result.Success(
                entry.toJson(),
                200
            )
        is GetEntryUseCase.Result.Failure ->
            Result.Failure(
                MessageJson("Entry not found"),
                404
            )
    }
}

fun GetEntriesUseCase.Result.toControllerResult(): Result<Nothing, List<EntryResponseJson>> {
    return Result.Success(
       entries.map { it.toJson() },
        200
    )
}

fun CreateEntryUseCase.Result.toControllerResult(): Result<MessageJson, EntryResponseJson> {
    return when (this) {
        is CreateEntryUseCase.Result.Success ->
            Result.Success(
                entry.toJson(),
                201
            )
        is CreateEntryUseCase.Result.Failure.EntryIdDuplicated ->
            Result.Failure(
                MessageJson("Entry id duplicated"),
                409
            )
    }
}

fun EditEntryUseCase.Result.toControllerResult(): Result<MessageJson, EntryResponseJson> {
    return when (this) {
        is EditEntryUseCase.Result.Success ->
            Result.Success(
                entry.toJson(),
                200
            )
        is EditEntryUseCase.Result.Failure.EntryNotFound ->
            Result.Failure(
                MessageJson("Entry not found"),
                404
            )
        is EditEntryUseCase.Result.Failure.EntryIdDuplicated ->
            Result.Failure(
                MessageJson("Entry id duplicated"),
                409
            )
    }
}

fun DeleteEntryUseCase.Result.toControllerResult(): Result<MessageJson, Empty> {
    return when (this) {
        is DeleteEntryUseCase.Result.Success ->
            Result.Success(
                Empty,
                204
            )
        is DeleteEntryUseCase.Result.Failure.EntryNotFound ->
            Result.Failure(
                MessageJson("Entry not found"),
                404
            )
    }
}

fun SignInUseCase.Result.toControllerResult(): Result<MessageJson, AuthResponseJson> {
    return when (this) {
        is SignInUseCase.Result.Success ->
            Result.Success(
                AuthResponseJson(token.value),
                200
            )
        is SignInUseCase.Result.Failure ->
            Result.Failure(
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
