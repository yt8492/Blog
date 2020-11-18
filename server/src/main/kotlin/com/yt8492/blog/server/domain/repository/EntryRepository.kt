package com.yt8492.blog.server.domain.repository

import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId

interface EntryRepository {

    suspend fun findById(id: EntryId): Entry?
    suspend fun findAllPublic(page: Int): List<Entry>
    suspend fun create(entry: Entry): Entry
    suspend fun update(entry: Entry): Entry
}