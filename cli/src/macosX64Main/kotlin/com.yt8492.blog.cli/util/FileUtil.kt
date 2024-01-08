package com.yt8492.blog.cli.util

import kotlinx.cinterop.*
import platform.posix.fclose
import platform.posix.fgets
import platform.posix.fopen

@OptIn(ExperimentalForeignApi::class)
object FileUtil {

    private const val BUF_SIZE = 1024

    fun readAll(path: String): String {
        val fp = fopen(path, "r") ?: throw NullPointerException("file not found")
        val stringBuilder = StringBuilder()
        memScoped {
            val buf = allocArray<ByteVar>(BUF_SIZE)
            while (true) {
                val nextLine = fgets(buf, BUF_SIZE, fp)?.toKString()
                if (nextLine == null) {
                    break
                } else {
                    stringBuilder.append(nextLine)
                }
            }
        }
        fclose(fp)
        return stringBuilder.toString()
    }
}