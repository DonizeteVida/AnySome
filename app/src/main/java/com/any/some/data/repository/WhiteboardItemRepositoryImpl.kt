package com.any.some.data.repository

import com.any.some.data.mapper.WhiteboardItemMapper
import com.any.some.data.room.dao.WhiteboardItemDataDao
import com.any.some.data.room.entity.WhiteboardItemEntity
import com.any.some.domain.model.WhiteboardItemData
import com.any.some.domain.repository.WhiteboardItemRepository
import javax.inject.Inject

class WhiteboardItemRepositoryImpl @Inject constructor(
    private val whiteboardItemDataDao: WhiteboardItemDataDao,
    private val whiteboardItemMappers: Map<Class<*>, @JvmSuppressWildcards WhiteboardItemMapper<*>>
) : WhiteboardItemRepository {
    override suspend fun <T> insert(item: WhiteboardItemData<T>) =
        whiteboardItemDataDao.insert(item.toEntity())

    override suspend fun getAll() = whiteboardItemDataDao
        .getAll()
        .map { it.toDomain() }

    override suspend fun <T> delete(item: WhiteboardItemData<T>) {
        whiteboardItemDataDao.delete(item.toEntity())
    }

    @Suppress("UNCHECKED_CAST")
    private suspend fun <T> WhiteboardItemData<T>.toEntity(): WhiteboardItemEntity {
        val clazz = Class.forName(type)
        val manager = whiteboardItemMappers[clazz] as? WhiteboardItemMapper<T>
            ?: throw IllegalStateException("Manager not found for type $type")
        return manager(this)
    }

    private suspend fun WhiteboardItemEntity.toDomain(): WhiteboardItemData<*> {
        val clazz = Class.forName(type)
        val manager = whiteboardItemMappers[clazz]
            ?: throw IllegalStateException("Manager not found for type $type")
        return manager(this)
    }
}