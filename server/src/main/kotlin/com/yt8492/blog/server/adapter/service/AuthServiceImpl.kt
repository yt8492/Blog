package com.yt8492.blog.server.adapter.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeSpan
import com.soywiz.klock.jvm.toDate
import com.yt8492.blog.common.model.AuthToken
import com.yt8492.blog.common.model.Password
import com.yt8492.blog.common.model.UserId
import com.yt8492.blog.server.domain.repository.UserRepository
import com.yt8492.blog.server.domain.service.AuthService
import java.util.*

class AuthServiceImpl(
    private val domain: String,
    private val secret: String,
    private val userRepository: UserRepository
) : AuthService {
    override suspend fun signIn(id: UserId, password: Password.Raw): AuthToken? {
        val user = userRepository.findById(id) ?: return null
        if (!user.password.verify(password)) {
            return null
        }
        val now = DateTime.now()
        val expire = now + DateTimeSpan(days = 10)
        val jwt = JWT.create()
            .withJWTId(UUID.randomUUID().toString())
            .withSubject(SUBJECT)
            .withIssuer(domain)
            .withIssuedAt(now.toDate())
            .withExpiresAt(expire.toDate())
            .withClaim(USER_ID_CLAIM, user.id.value)
            .sign(Algorithm.HMAC256(secret))
        return AuthToken(jwt)
    }

    companion object {
        private const val SUBJECT = "AuthToken"
        private const val USER_ID_CLAIM = "userId"
    }
}
