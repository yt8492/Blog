package com.yt8492.blog.server.adapter.db.dao

import com.yt8492.blog.server.adapter.db.table.EntryTable
import com.yt8492.blog.server.adapter.db.table.EntryTagTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SizedIterable
import java.time.LocalDateTime

class EntryDao(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, EntryDao>(EntryTable)

    var title: String by EntryTable.title
    var content: String by EntryTable.content
    var tags: SizedIterable<TagDao> by TagDao via EntryTagTable
    var isPreview: Boolean by EntryTable.isPreview
    var createdAt: LocalDateTime by EntryTable.createdAt
    var updatedAt: LocalDateTime by EntryTable.updatedAt
}
