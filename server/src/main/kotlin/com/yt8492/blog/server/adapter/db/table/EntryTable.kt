package com.yt8492.blog.server.adapter.db.table

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

object EntryTable : IdTable<String>("entries") {
    override val id: Column<EntityID<String>> = varchar("id", 255).entityId()
    val title: Column<String> = text("title")
    val content: Column<String> = text("content")
    val isPreview: Column<Boolean> = bool("is_preview")
    val createdAt: Column<LocalDateTime> = datetime("created_at")
    val updatedAt: Column<LocalDateTime> = datetime("updated_at")

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}
