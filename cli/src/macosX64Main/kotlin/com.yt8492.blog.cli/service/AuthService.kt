package com.yt8492.blog.cli.service

import com.yt8492.blog.cli.api.Api
import com.yt8492.blog.cli.util.FileUtil
import com.yt8492.blog.common.model.AuthToken
import com.yt8492.blog.common.model.Password
import com.yt8492.blog.common.model.UserId
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString
import platform.posix.*

@OptIn(ExperimentalForeignApi::class)
class AuthService(private val api: Api) {

    suspend fun login(userId: UserId, password: Password.Raw): Boolean {
        val token = api.login(userId, password)
        val homeDir = getenv("HOME")!!.toKString()
        mkpath_np("$homeDir/.config/blog", S_IRWXU.toUShort())
        val fp = fopen("$homeDir/.config/blog/token", "w")
        return if (fp != null) {
            fputs(token.value, fp)
            fclose(fp)
            true
        } else {
            false
        }
    }

    fun getToken(): AuthToken? {
        val homeDir = getenv("HOME")!!.toKString()
        return try {
            val value = FileUtil.readAll("$homeDir/.config/blog/token")
            AuthToken(value)
        } catch (e: NullPointerException) {
            null
        }
    }
}
