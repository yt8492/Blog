package com.yt8492.blog.server.adapter.db.dao

import com.yt8492.blog.common.model.Role
import com.yt8492.blog.server.adapter.db.table.UserTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserDao(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, UserDao>(UserTable)

    var role: Role by UserTable.role
    var password: String by UserTable.password
}
