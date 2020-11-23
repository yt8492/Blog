package com.yt8492.blog.server.adapter.db.table

import com.yt8492.blog.common.model.Role
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object UserTable : IdTable<String>() {
    override val id: Column<EntityID<String>> = varchar("id", 256).entityId()
    val role: Column<Role> = enumeration("role", Role::class)
    val password: Column<String> = varchar("password", 256)
}
