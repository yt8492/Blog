package com.yt8492.blog.server.di

import com.yt8492.blog.server.usecase.*
import com.yt8492.blog.server.usecase.impl.*
import org.koin.dsl.module

val useCaseModule = module {
    single<GetEntryUseCase> {
        GetEntryUseCaseImpl(get())
    }
    single<GetEntriesUseCase> {
        GetEntriesUseCaseImpl(get())
    }
    single<CreateEntryUseCase> {
        CreateEntryUseCaseImpl(get())
    }
    single<EditEntryUseCase> {
        EditEntryUseCaseImpl(get())
    }
    single<DeleteEntryUseCase> {
        DeleteEntryUseCaseImpl(get())
    }
    single<SignInUseCase> {
        SignInUseCaseImpl(get())
    }
}
