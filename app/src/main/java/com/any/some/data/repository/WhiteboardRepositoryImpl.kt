package com.any.some.data.repository

import com.any.some.data.room.dao.WhiteboardItemDao
import com.any.some.data.room.entity.WhiteboardItemEntity
import com.any.some.domain.model.WhiteboardItem
import com.any.some.domain.repository.WhiteboardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WhiteboardRepositoryImpl @Inject constructor(
    private val whiteboardItemDao: WhiteboardItemDao
) : WhiteboardRepository {
    override suspend fun insert(item: WhiteboardItem) {
        whiteboardItemDao.insert(item.toEntity())
    }

    override fun get(): Flow<List<WhiteboardItem>> = whiteboardItemDao
        .get()
        .map { it.map(WhiteboardItemEntity::toDomain) }

    override suspend fun delete(item: WhiteboardItem) {
        whiteboardItemDao.delete(item.toEntity())
    }
}

private fun WhiteboardItem.toEntity() = WhiteboardItemEntity(
    id, x, y, width, height, body
)

private fun WhiteboardItemEntity.toDomain() = WhiteboardItem(
    id, x, y, width, height, body
)