package com.yt8492.blog.server.di

import com.yt8492.blog.server.AppConfig
import com.yt8492.blog.server.adapter.repository.EntryRepositoryImpl
import com.yt8492.blog.server.adapter.repository.UserRepositoryImpl
import com.yt8492.blog.server.adapter.service.AuthServiceImpl
import com.yt8492.blog.server.domain.repository.EntryRepository
import com.yt8492.blog.server.domain.repository.UserRepository
import com.yt8492.blog.server.domain.service.AuthService
import org.koin.dsl.module

val domainModule = module {
    single<EntryRepository> {
        EntryRepositoryImpl()
    }
    single<UserRepository> {
        UserRepositoryImpl()
    }
    single<AuthService> {
        AuthServiceImpl(get(), get())
    }
}
