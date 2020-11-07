package com.yt8492.blog.server.domain

import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId

interface EntryRepository {

    suspend fun findById(id: EntryId): Entry
    suspend fun findAll(page: Int): List<Entry>
}