package com.yt8492.blog.server.di

import com.google.cloud.datastore.Datastore
import com.google.cloud.datastore.DatastoreOptions
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
    single<Datastore> {
        // 環境変数 GOOGLE_APPLICATION_CREDENTIALS に認証情報へのパスを設定する
        DatastoreOptions.getDefaultInstance().service
    }
}
