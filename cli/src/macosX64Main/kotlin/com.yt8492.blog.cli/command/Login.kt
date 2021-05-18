package com.yt8492.blog.cli.command

import com.yt8492.blog.cli.api.Api
import com.yt8492.blog.cli.service.AuthService
import com.yt8492.blog.common.model.Password
import com.yt8492.blog.common.model.UserId
import kotlinx.cinterop.toKString
import kotlinx.cli.ArgType
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlinx.cli.default
import kotlinx.coroutines.runBlocking
import platform.posix.getpass
import platform.posix.perror

@OptIn(ExperimentalCli::class)
class Login : Subcommand("login", "yt8492 blog login") {

    private val isLocal: Boolean by option(ArgType.Boolean, shortName = "l").default(false)
    private val userId: String by argument(ArgType.String)

    override fun execute() {
        val authService = AuthService(Api(isLocal))
        val password = getpass("Password: ")!!.toKString()
        runBlocking {
            val ok = authService.login(UserId(userId), Password.Raw(password))
            if (ok) {
                println("login success")
            } else {
                perror("login failure")
            }
        }
    }
}
