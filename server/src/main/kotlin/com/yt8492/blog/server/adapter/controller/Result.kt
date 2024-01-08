package com.yt8492.blog.server.adapter.controller

sealed interface Result<out L : Any, out R : Any> {

    val statusCode: Int

    data class Success<T : Any>(
        val json: T,
        override val statusCode: Int
    ) : Result<Nothing, T>

    data class Failure<T : Any>(
        val json: T,
        override val statusCode: Int
    ) : Result<T, Nothing>
}
