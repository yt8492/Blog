package com.yt8492.blog.server.adapter.service

import com.yt8492.blog.common.model.AuthToken
import com.yt8492.blog.common.model.Password
import com.yt8492.blog.common.model.UserId
import com.yt8492.blog.server.adapter.jwt.UserJWTService
import com.yt8492.blog.server.domain.repository.UserRepository
import com.yt8492.blog.server.domain.service.AuthService

class AuthServiceImpl(
    private val userRepository: UserRepository,
    private val userJWTService: UserJWTService
) : AuthService {
    override suspend fun signIn(id: UserId, password: Password.Raw): AuthToken? {
        val user = userRepository.findById(id) ?: return null
        if (!user.password.verify(password)) {
            return null
        }
        val jwt = userJWTService.createUserIdJWT(user.id)
        return AuthToken(jwt)
    }
}
