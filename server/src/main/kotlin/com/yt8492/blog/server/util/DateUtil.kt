package com.yt8492.blog.server.util

import korlibs.time.DateTime
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

fun LocalDateTime.toDateTime(): DateTime {
    val instant = toInstant(ZoneOffset.UTC)
    val unixMillis = instant.toEpochMilli()
    return DateTime(unixMillis)
}

fun DateTime.toLocalDateTime(): LocalDateTime {
    val instant = Instant.ofEpochMilli(unixMillisLong)
    return LocalDateTime.ofInstant(instant, ZoneOffset.UTC)
}
