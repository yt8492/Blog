package com.yt8492.blog.cli.command

import com.yt8492.blog.cli.api.Api
import com.yt8492.blog.cli.service.AuthService
import com.yt8492.blog.cli.util.FileUtil
import com.yt8492.blog.common.model.AuthToken
import com.yt8492.blog.common.model.EntryId
import kotlinx.cinterop.toKString
import kotlinx.cli.ArgType
import kotlinx.cli.ExperimentalCli
import kotlinx.cli.Subcommand
import kotlinx.cli.default
import kotlinx.coroutines.runBlocking
import platform.posix.getenv

@OptIn(ExperimentalCli::class)
class Post : Subcommand("post", "create new entry") {

    private val filePath by argument(ArgType.String, "Entry markdown file")
    private val isLocal by option(ArgType.Boolean, shortName = "l").default(false)

    override fun execute() {
        val api = Api(isLocal)
        val authService = AuthService(api)
        val token = authService.getToken()
        if (token == null) {
            println("Blog auth token is null. Please login.")
            return
        }
        val title = shouldNotEmptyInput("title")
        print("tags(default is empty): ")
        val tags = readLine()?.split("([ ,])".toRegex()) ?: emptyList()
        print("isPreview(default is false): ")
        val isPreview = readLine()?.toBoolean() ?: false
        print("id(default is random): ")
        val id = readLine()?.takeIf { it.isNotBlank() }?.let { EntryId(it) }
        val content = FileUtil.readAll(filePath)
        runBlocking {
            val entry = api.createEntry(token, id, title, content, tags, isPreview)
            println("published: ${entry.id.value}")
        }
    }

    private fun getToken(): AuthToken? {
        val homeDir = getenv("HOME")!!.toKString()
        return try {
            val value = FileUtil.readAll("$homeDir/.config/blog/token")
            AuthToken(value)
        } catch (e: NullPointerException) {
            null
        }
    }

    private fun shouldNotEmptyInput(prompt: String): String {
        print("$prompt: ")
        var input = readLine()
        while (input.isNullOrEmpty()) {
            println("$prompt should not empty!")
            print("$prompt: ")
            input = readLine()
        }
        return input
    }
}