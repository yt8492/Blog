package com.yt8492.blog.cli.command

import com.yt8492.blog.cli.api.Api
import com.yt8492.blog.cli.service.AuthService
import com.yt8492.blog.common.model.EntryId
import kotlinx.cli.ArgType
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlinx.cli.default
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCli::class)
class Delete : Subcommand("delete", "delete entry") {

    private val id by argument(ArgType.String, "Entry id")
    private val isLocal by option(ArgType.Boolean, shortName = "l").default(false)

    override fun execute() {
        val api = Api(isLocal)
        val authService = AuthService(api)
        val token = authService.getToken()
        if (token == null) {
            println("Blog auth token is null. Please login.")
            return
        }
        runBlocking {
            api.deleteEntry(token, EntryId(id))
            println("delete $id success")
        }
    }
}
