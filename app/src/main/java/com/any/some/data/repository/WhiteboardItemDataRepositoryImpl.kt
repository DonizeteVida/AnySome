package com.any.some.data.repository

import com.any.some.data.room.dao.WhiteboardItemDataDao
import com.any.some.data.room.entity.WhiteboardItemDataEntity
import com.any.some.domain.model.WhiteboardItemData
import com.any.some.domain.repository.WhiteboardItemDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WhiteboardItemDataRepositoryImpl @Inject constructor(
    private val whiteboardItemDataDao: WhiteboardItemDataDao
) : WhiteboardItemDataRepository {
    override suspend fun insert(item: WhiteboardItemData) {
        whiteboardItemDataDao.insert(item.toEntity())
    }

    override fun get(): Flow<List<WhiteboardItemData>> = whiteboardItemDataDao
        .get()
        .map { it.map(WhiteboardItemDataEntity::toDomain) }

    override suspend fun delete(item: WhiteboardItemData) {
        whiteboardItemDataDao.delete(item.toEntity())
    }
}

private fun WhiteboardItemData.toEntity() = WhiteboardItemDataEntity(
    id, type, body, x, y, width, height
)

private fun WhiteboardItemDataEntity.toDomain() = WhiteboardItemData(
    id, type, body, x, y, width, height
)