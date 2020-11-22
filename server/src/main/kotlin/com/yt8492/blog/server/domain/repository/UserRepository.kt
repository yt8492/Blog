package com.yt8492.blog.server.domain.repository

import com.yt8492.blog.common.model.User
import com.yt8492.blog.common.model.UserId

interface UserRepository {

    suspend fun findById(id: UserId): User?
}