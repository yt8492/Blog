package com.yt8492.blog.server.di

import com.yt8492.blog.server.adapter.repository.EntryRepositoryOnDatastore
import com.yt8492.blog.server.adapter.repository.UserRepositoryOnDatastore
import com.yt8492.blog.server.adapter.service.AuthServiceImpl
import com.yt8492.blog.server.domain.repository.EntryRepository
import com.yt8492.blog.server.domain.repository.UserRepository
import com.yt8492.blog.server.domain.service.AuthService
import org.koin.dsl.module

val domainModule = module {
    single<EntryRepository> {
        EntryRepositoryOnDatastore(get())
    }
    single<UserRepository> {
        UserRepositoryOnDatastore(get())
    }
    single<AuthService> {
        AuthServiceImpl(get(), get())
    }
}
