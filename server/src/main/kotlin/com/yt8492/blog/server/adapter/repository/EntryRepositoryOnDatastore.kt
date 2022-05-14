package com.yt8492.blog.server.adapter.repository

import com.google.cloud.Timestamp
import com.google.cloud.datastore.*
import com.soywiz.klock.jvm.toDate
import com.soywiz.klock.jvm.toDateTime
import com.yt8492.blog.common.model.Entry
import com.yt8492.blog.common.model.EntryId
import com.yt8492.blog.server.domain.repository.EntryRepository

class EntryRepositoryOnDatastore(
    private val datastore: Datastore,
) : EntryRepository {
    override suspend fun findById(id: EntryId): Entry? {
        val key = newKey(id)
        return datastore.get(key)?.let {
            entityToModel(it)
        }
    }

    override suspend fun findAllPublic(page: Int): List<Entry> {
        val filter = StructuredQuery.PropertyFilter.eq(PROPERTY_IS_PREVIEW, false)
        val query = Query
            .newEntityQueryBuilder()
            .setKind(KIND)
            .setFilter(filter)
            .setLimit(10)
            .setOffset((page - 1) * 10)
            .build()
        return datastore.run(query)
            .asSequence()
            .map { entityToModel(it) }
            .toList()
    }

    override suspend fun create(entry: Entry): Entry {
        val entity = modelToEntity(entry)
        datastore.put(entity)
        return entry
    }

    override suspend fun update(entry: Entry): Entry {
        val entity = modelToEntity(entry)
        datastore.update(entity)
        return entry
    }

    override suspend fun delete(id: EntryId): Boolean {
        val key = newKey(id)
        datastore.get(key) ?: return false
        datastore.delete(key)
        return true
    }

    private fun newKey(id: EntryId): Key {
        return datastore.newKeyFactory().setKind(KIND).newKey(id.value)
    }

    private fun modelToEntity(entry: Entry): Entity {
        val key = newKey(entry.id)
        return Entity.newBuilder(key)
            .set(PROPERTY_TITLE, entry.title)
            .set(PROPERTY_CONTENT, StringValue.newBuilder(entry.content).setExcludeFromIndexes(true).build())
            .set(PROPERTY_TAGS, entry.tags.map { StringValue(it) })
            .set(PROPERTY_IS_PREVIEW, entry.isPreview)
            .set(PROPERTY_CREATED_AT, Timestamp.of(entry.createdAt.toDate()))
            .set(PROPERTY_UPDATED_AT, Timestamp.of(entry.updatedAt.toDate()))
            .build()
    }

    private fun entityToModel(entity: Entity): Entry {
        return Entry(
            id = EntryId(entity.key.name),
            title = entity.getString(PROPERTY_TITLE),
            content = entity.getString(PROPERTY_CONTENT),
            tags = entity.getList<Value<String>>(PROPERTY_TAGS).map { it.get() },
            isPreview = entity.getBoolean(PROPERTY_IS_PREVIEW),
            createdAt = entity.getTimestamp(PROPERTY_CREATED_AT).toDate().toDateTime(),
            updatedAt = entity.getTimestamp(PROPERTY_UPDATED_AT).toDate().toDateTime(),
        )
    }

    companion object {
        private const val KIND = "Entry"
        private const val PROPERTY_ID = "id"
        private const val PROPERTY_TITLE = "title"
        private const val PROPERTY_CONTENT = "content"
        private const val PROPERTY_TAGS = "tags"
        private const val PROPERTY_IS_PREVIEW = "isPreview"
        private const val PROPERTY_CREATED_AT = "createdAt"
        private const val PROPERTY_UPDATED_AT = "updatedAt"
    }
}
