package com.yt8492.blog.server.util

import com.soywiz.klock.DateTime
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDateTime

class DateUtilTest : StringSpec({
    "LocalDateTime to DateTime" {
        val target = LocalDateTime.of(2020, 4, 1, 0, 0)
        val result = target.toDateTime()
        result shouldBe DateTime(2020, 4, 1, 0, 0)
    }

    "DateTime to LocalDateTime" {
        val target = DateTime(2020, 4, 1, 0, 0)
        val result = target.toLocalDateTime()
        result shouldBe LocalDateTime.of(2020, 4, 1, 0, 0)
    }
})