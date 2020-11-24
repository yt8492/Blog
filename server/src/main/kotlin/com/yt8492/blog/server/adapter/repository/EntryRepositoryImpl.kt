package com.yt8492.blog.server.adapter.repository

import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId
import com.yt8492.blog.server.adapter.db.converter.toModel
import com.yt8492.blog.server.adapter.db.dao.EntryDao
import com.yt8492.blog.server.adapter.db.dao.TagDao
import com.yt8492.blog.server.adapter.db.table.EntryTable
import com.yt8492.blog.server.adapter.db.table.TagTable
import com.yt8492.blog.server.domain.repository.EntryRepository
import com.yt8492.blog.server.util.toLocalDateTime
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.transactions.transaction

class EntryRepositoryImpl : EntryRepository {
    override suspend fun findById(id: EntryId): Entry? {
        return transaction {
            EntryDao.findById(id.value)?.toModel()
        }
    }

    override suspend fun findAllPublic(page: Int): List<Entry> {
        return transaction {
            EntryDao.find { EntryTable.isPreview eq false }
                .limit(10, page * 10L)
                .map(EntryDao::toModel)
        }
    }

    override suspend fun create(entry: Entry): Entry {
        return transaction {
            val existTags = TagDao.find { TagTable.name inList entry.tags }
            val notExistTags = (entry.tags - existTags.map { it.name }).map {
                TagDao.new { name = it }
            }
            EntryDao.new(entry.id.value) {
                title = entry.title
                content = entry.content
                tags = SizedCollection(existTags + notExistTags)
                isPreview = entry.isPreview
                createdAt = entry.createdAt.toLocalDateTime()
                updatedAt = entry.updatedAt.toLocalDateTime()
            }.toModel()
        }
    }

    override suspend fun update(entry: Entry): Entry {
        transaction {
            val dao = EntryDao.findById(entry.id.value)
            dao?.apply {
                val existTags = TagDao.find { TagTable.name inList entry.tags }
                val notExistTags = (entry.tags - existTags.map { it.name }).map {
                    TagDao.new { name = it }
                }
                title = entry.title
                content = entry.content
                tags = SizedCollection(existTags + notExistTags)
                isPreview = entry.isPreview
                createdAt = entry.createdAt.toLocalDateTime()
                updatedAt = entry.updatedAt.toLocalDateTime()
            }
        }
        return entry
    }
}