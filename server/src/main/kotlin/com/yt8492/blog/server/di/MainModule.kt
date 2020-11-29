package com.yt8492.blog.server.di

import com.yt8492.blog.server.AppConfig
import com.yt8492.blog.server.adapter.jwt.UserJWTService
import org.koin.dsl.module

val mainModule = module {
    single {
        UserJWTService(AppConfig.domain, AppConfig.authSecret)
    }
}
