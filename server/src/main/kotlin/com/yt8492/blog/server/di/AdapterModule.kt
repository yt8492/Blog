package com.yt8492.blog.server.di

import com.yt8492.blog.server.AppConfig
import com.yt8492.blog.server.adapter.controller.EntryController
import com.yt8492.blog.server.adapter.controller.UserController
import com.yt8492.blog.server.adapter.db.DBHelper
import com.yt8492.blog.server.adapter.jwt.UserJWTService
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
    single {
        DBHelper(
            AppConfig.dbDriver,
            AppConfig.dbUrl,
            AppConfig.dbUser,
            AppConfig.dbPassword
        )
    }
}
