package com.yt8492.blog.server.adapter.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeSpan
import com.soywiz.klock.jvm.toDate
import com.yt8492.blog.common.model.UserId
import java.util.*

class UserJWTService(
    private val domain: String,
    secret: String
) {
    private val algorithm = Algorithm.HMAC256(secret)

    fun createVerifier(): JWTVerifier {
        return JWT.require(algorithm)
            .withIssuer(domain)
            .build()
    }

    fun createUserIdJWT(userId: UserId): String {
        val now = DateTime.now()
        val expire = now + DateTimeSpan(days = 10)
        return JWT.create()
            .withJWTId(UUID.randomUUID().toString())
            .withSubject(SUBJECT)
            .withIssuer(domain)
            .withIssuedAt(now.toDate())
            .withExpiresAt(expire.toDate())
            .withClaim(USER_ID_CLAIM, userId.value)
            .sign(algorithm)
    }

    companion object {
        private const val SUBJECT = "AuthToken"
        const val USER_ID_CLAIM = "userId"
    }
}