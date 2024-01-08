package com.yt8492.blog.cli.command

import com.yt8492.blog.cli.api.Api
import com.yt8492.blog.cli.service.AuthService
import com.yt8492.blog.cli.util.FileUtil
import com.yt8492.blog.common.model.EntryId
import kotlinx.cli.ArgType
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlinx.cli.default
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCli::class)
class Edit : Subcommand("edit", "edit entry") {

    private val id by argument(ArgType.String, "Entry id")
    private val filePath by option(ArgType.String, shortName = "f")
    private val isLocal by option(ArgType.Boolean, shortName = "l").default(false)

    override fun execute() {
        val api = Api(isLocal)
        val authService = AuthService(api)
        val token = authService.getToken()
        if (token == null) {
            println("Blog auth token is null. Please login.")
            return
        }
        val oldId = EntryId(id)
        print("id(empty input will be nothing changed)")
        val newId = readlnOrNull()?.takeIf { it.isNotBlank() }?.let { EntryId(it) }
        print("title(empty input will be nothing changed)")
        val title = readlnOrNull()?.takeIf { it.isNotBlank() }
        print("tags(empty input will be nothing changed): ")
        val tags = readlnOrNull()?.split("([ ,])".toRegex())?.filter { it.isNotBlank() }?.takeIf { it.isNotEmpty() }
        print("isPreview(empty input will be nothing changed): ")
        val isPreview = readlnOrNull()?.toBoolean() ?: false
        val content = filePath?.let { FileUtil.readAll(it) }
        runBlocking {
            val entry = api.editEntry(token, oldId, newId, title, content, tags, isPreview)
            println("edited: ${entry.id.value}")
        }
    }
}
