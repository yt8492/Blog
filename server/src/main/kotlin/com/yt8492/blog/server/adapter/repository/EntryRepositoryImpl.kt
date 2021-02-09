package com.yt8492.blog.server.adapter.repository

import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId
import com.yt8492.blog.server.adapter.db.converter.toModel
import com.yt8492.blog.server.adapter.db.dao.EntryDao
import com.yt8492.blog.server.adapter.db.dao.TagDao
import com.yt8492.blog.server.adapter.db.table.EntryTable
import com.yt8492.blog.server.adapter.db.table.EntryTagTable
import com.yt8492.blog.server.adapter.db.table.TagTable
import com.yt8492.blog.server.domain.repository.EntryRepository
import com.yt8492.blog.server.util.toLocalDateTime
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.SQLException

class EntryRepositoryImpl : EntryRepository {
    override suspend fun findById(id: EntryId): Entry? {
        return transaction {
            EntryDao.findById(id.value)?.toModel()
        }
    }

    override suspend fun findAllPublic(page: Int): List<Entry> {
        return transaction {
            EntryDao.find { EntryTable.isPreview eq false }
                .orderBy(EntryTable.createdAt to SortOrder.ASC)
                .limit(10, (page - 1) * 10L)
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
                isPreview = entry.isPreview
                createdAt = entry.createdAt.toLocalDateTime()
                updatedAt = entry.updatedAt.toLocalDateTime()
            }.also {
                EntryTagTable.batchInsert(existTags + notExistTags, ignore = true) {
                    this[EntryTagTable.entryId] = entry.id.value
                    this[EntryTagTable.tagId] = it.id.value
                }
            }.toModel()
        }
    }

    override suspend fun update(entry: Entry): Entry {
        transaction {
            val existEntry = EntryDao.findById(entry.id.value) ?: return@transaction
            val existTags = TagDao.find { TagTable.name inList entry.tags }
            val notExistTags = (entry.tags - existTags.map { it.name }).map {
                TagDao.new { name = it }
            }
            val deletedTags = existEntry.tags - existTags
            EntryTagTable.deleteWhere {
                EntryTagTable.entryId eq entry.id.value and
                    (EntryTagTable.tagId inList deletedTags.map { it.id })
            }
            existEntry.apply {
                title = entry.title
                content = entry.content
                isPreview = entry.isPreview
                createdAt = entry.createdAt.toLocalDateTime()
                updatedAt = entry.updatedAt.toLocalDateTime()
            }.also {
                val shouldInsertTags = (existTags + notExistTags).filter { !existEntry.tags.contains(it) }
                EntryTagTable.batchInsert(shouldInsertTags) {
                    this[EntryTagTable.entryId] = entry.id.value
                    this[EntryTagTable.tagId] = it.id.value
                }
            }
        }
        return entry
    }

    override suspend fun delete(id: EntryId): Boolean {
        return transaction {
            try {
                EntryTagTable.deleteWhere {
                    EntryTagTable.entryId eq id.value
                }
                EntryTable.deleteWhere {
                    EntryTable.id eq id.value
                } > 0
            } catch (e: SQLException) {
                false
            }
        }
    }
}
