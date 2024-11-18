package com.any.some.di

import com.any.some.domain.model.WhiteboardItemType
import com.any.some.presentation.feature.picture.PictureUIWhiteboardItemMapper
import com.any.some.presentation.feature.text.TextUIWhiteboardItemMapper
import com.any.some.presentation.mapper.UIWhiteboardItemMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
interface UIWhiteboardItemMapperModule {
    @Binds
    @IntoMap
    @WhiteboardItemTypeKey(WhiteboardItemType.TEXT)
    fun bindsTextUIWhiteboardItemMapper(impl: TextUIWhiteboardItemMapper): UIWhiteboardItemMapper<*>

    @Binds
    @IntoMap
    @WhiteboardItemTypeKey(WhiteboardItemType.PICTURE)
    fun bindsPictureUIWhiteboardItemMapper(impl: PictureUIWhiteboardItemMapper): UIWhiteboardItemMapper<*>
}