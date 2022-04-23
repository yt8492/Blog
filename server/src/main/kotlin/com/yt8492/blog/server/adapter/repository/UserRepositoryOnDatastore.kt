package com.yt8492.blog.server.adapter.repository

import com.google.cloud.datastore.Datastore
import com.google.cloud.datastore.Query
import com.google.cloud.datastore.StructuredQuery
import com.yt8492.blog.common.model.Password
import com.yt8492.blog.common.model.Role
import com.yt8492.blog.common.model.User
import com.yt8492.blog.common.model.UserId
import com.yt8492.blog.server.domain.repository.UserRepository

class UserRepositoryOnDatastore(
    private val datastore: Datastore,
) : UserRepository {
    override suspend fun findById(id: UserId): User? {
        val key = datastore.newKeyFactory().setKind(KIND).newKey(id.value)
        val entity = datastore.get(key) ?: return null
        return User(
            id = id,
            role = Role.valueOf(entity.getString(PROPERTY_ROLE)),
            password = Password.Hashed(entity.getString(PROPERTY_PASSWORD))
        )
    }

    companion object {
        private const val KIND = "User"
        private const val PROPERTY_ROLE = "role"
        private const val PROPERTY_PASSWORD = "password"
    }
}