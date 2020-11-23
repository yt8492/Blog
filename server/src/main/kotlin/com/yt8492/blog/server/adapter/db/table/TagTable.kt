package com.yt8492.blog.server.adapter.db.table

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column

object TagTable : UUIDTable("tag") {
    val name: Column<String> = text("name")
}
