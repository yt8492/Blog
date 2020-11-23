package com.yt8492.blog.server.adapter.db.dao

import com.yt8492.blog.server.adapter.db.table.TagTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class TagDao(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<TagDao>(TagTable)

    var name: String by TagTable.name
}
