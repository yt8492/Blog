package com.yt8492.blog.server.di

import com.yt8492.blog.server.adapter.controller.EntryController
import com.yt8492.blog.server.adapter.controller.UserController
import org.koin.dsl.module

val adapterModule = module {
    single {
        EntryController(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }
    single {
        UserController(
            get()
        )
    }
}
