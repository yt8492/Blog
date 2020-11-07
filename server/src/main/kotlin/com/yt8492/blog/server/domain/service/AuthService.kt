package com.yt8492.blog.server.domain.service

import com.yt8492.blog.common.model.AuthToken
import com.yt8492.blog.common.model.Password
import com.yt8492.blog.common.model.UserId

interface AuthService {
    suspend fun signIn(id: UserId, password: Password.Raw): AuthToken
}
