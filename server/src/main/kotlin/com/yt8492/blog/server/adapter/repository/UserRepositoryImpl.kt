package com.yt8492.blog.server.adapter.repository

import com.yt8492.blog.common.model.User
import com.yt8492.blog.common.model.UserId
import com.yt8492.blog.server.adapter.db.converter.toModel
import com.yt8492.blog.server.adapter.db.dao.UserDao
import com.yt8492.blog.server.domain.repository.UserRepository
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepositoryImpl : UserRepository {
    override suspend fun findById(id: UserId): User? {
        return transaction {
            UserDao.findById(id.value)?.toModel()
        }
    }
}
