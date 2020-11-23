package com.yt8492.blog.server.adapter.db.table

import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import java.util.*

object EntryTagTable : Table("entry_tags") {
    val entryId: Column<EntityID<String>> = reference("entry_id", EntryTable)
    val tagId: Column<EntityID<UUID>> = reference("tag_id", TagTable)
    override val primaryKey: PrimaryKey = PrimaryKey(entryId, tagId, name = "pk_entry_tags")
}
