package com.any.some.data.repository

import com.any.some.data.mapper.WhiteboardItemMapper
import com.any.some.data.room.dao.WhiteboardItemDataDao
import com.any.some.data.room.entity.WhiteboardItemEntity
import com.any.some.domain.model.WhiteboardItem
import com.any.some.domain.repository.WhiteboardItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WhiteboardItemRepositoryImpl @Inject constructor(
    private val whiteboardItemDataDao: WhiteboardItemDataDao,
    private val whiteboardItemMappers: Map<Class<*>, @JvmSuppressWildcards WhiteboardItemMapper<*>>
) : WhiteboardItemRepository {
    override suspend fun <T> insert(item: WhiteboardItem<T>) {
        whiteboardItemDataDao.insert(item.toEntity())
    }

    override fun get(): Flow<List<WhiteboardItem<*>>> = whiteboardItemDataDao
        .get()
        .map { items -> items.map { entity -> entity.toDomain() } }

    override suspend fun <T> delete(item: WhiteboardItem<T>) {
        whiteboardItemDataDao.delete(item.toEntity())
    }

    @Suppress("UNCHECKED_CAST")
    private suspend fun <T> WhiteboardItem<T>.toEntity(): WhiteboardItemEntity {
        val clazz = Class.forName(type)
        val manager = whiteboardItemMappers[clazz] as? WhiteboardItemMapper<T>
            ?: throw IllegalStateException("Manager not found for type $type")
        return manager(this)
    }

    private suspend fun WhiteboardItemEntity.toDomain(): WhiteboardItem<*> {
        val clazz = Class.forName(type)
        val manager = whiteboardItemMappers[clazz]
            ?: throw IllegalStateException("Manager not found for type $type")
        return manager(this)
    }
}