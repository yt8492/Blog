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
        val filter = StructuredQuery.PropertyFilter.eq(PROPERTY_ID, id.value)
        val query = Query.newEntityQueryBuilder().setKind(KIND).setFilter(filter).build()
        val entity = datastore.run(query).asSequence().firstOrNull() ?: return null
        return User(
            id = UserId(entity.getString(PROPERTY_ID)),
            role = Role.valueOf(entity.getString(PROPERTY_ROLE)),
            password = Password.Hashed(entity.getString(PROPERTY_PASSWORD))
        )
    }

    companion object {
        private const val KIND = "User"
        private const val PROPERTY_ID = "id"
        private const val PROPERTY_ROLE = "role"
        private const val PROPERTY_PASSWORD = "password"
    }
}