package com.any.some.di

import com.any.some.data.mapper.TextWhiteboardItemMapper
import com.any.some.data.mapper.WhiteboardItemMapper
import com.any.some.domain.model.WhiteboardItemType
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface WhiteboardItemMapperModule {
    @Binds
    @IntoMap
    @WhiteboardItemTypeKey(WhiteboardItemType.TEXT)
    fun bindsTextWhiteboardItemMapper(impl: TextWhiteboardItemMapper): WhiteboardItemMapper<*>
}