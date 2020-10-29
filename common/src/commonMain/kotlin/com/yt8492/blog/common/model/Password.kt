package com.yt8492.blog.common.model

import com.soywiz.krypto.SHA256

sealed class Password {

    abstract val value: String

    abstract fun verify(other: Password): Boolean

    data class Raw(override val value: String) : Password() {

        val hashed by lazy {
            Hashed(
                SHA256.digest((value + salt).encodeToByteArray()).hex
            )
        }

        override fun verify(other: Password): Boolean {
            return when (other) {
                is Raw -> this == other
                is Hashed -> this.hashed == other
            }
        }
    }

    data class Hashed(override val value: String) : Password() {
        override fun verify(other: Password): Boolean {
            return when (other) {
                is Raw -> this == other.hashed
                is Hashed -> this == other
            }
        }
    }

    companion object {
        private const val salt = "com.yt8492.blog"
    }
}
