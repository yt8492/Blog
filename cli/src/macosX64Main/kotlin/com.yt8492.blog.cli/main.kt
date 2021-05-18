package com.yt8492.blog.cli

import com.yt8492.blog.cli.command.Delete
import com.yt8492.blog.cli.command.Edit
import com.yt8492.blog.cli.command.Login
import com.yt8492.blog.cli.command.Post
import kotlinx.cli.ArgParser
import kotlinx.cli.ExperimentalCli

@OptIn(ExperimentalCli::class)
fun main(args: Array<String>) {
    val parser = ArgParser("blog")
    parser.subcommands(Login(), Post(), Edit(), Delete())
    parser.parse(args)
}
