package com.yt8492.blog.server.adapter.controller

import com.yt8492.blog.server.adapter.json.Json

sealed class Result {

    abstract val statusCode: Int

    data class Object(
        val json: Json,
        override val statusCode: Int
    ) : Result()

    data class Array(
        val list: List<Json>,
        override val statusCode: Int
    ) : Result()

    companion object {
        operator fun invoke(
            json: Json,
            statusCode: Int
        ): Object {
            return Object(
                json,
                statusCode
            )
        }

        operator fun invoke(
            list: List<Json>,
            statusCode: Int
        ): Array {
            return Array(
                list,
                statusCode
            )
        }
    }
}
