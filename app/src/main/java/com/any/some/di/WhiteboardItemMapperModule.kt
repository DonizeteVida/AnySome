package com.any.some.di

import com.any.some.data.mapper.PictureWhiteboardItemMapper
import com.any.some.data.mapper.TextWhiteboardItemMapper
import com.any.some.data.mapper.WhiteboardItemMapper
import com.any.some.presentation.feature.picture.PictureUIWhiteboardItem
import com.any.some.presentation.feature.picture.PictureUIWhiteboardItemMapper
import com.any.some.presentation.feature.text.TextUIWhiteboardItem
import com.any.some.presentation.feature.text.TextUIWhiteboardItemMapper
import com.any.some.presentation.mapper.UIWhiteboardItemMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.LazyClassKey

@Module
@InstallIn(SingletonComponent::class)
interface WhiteboardItemMapperModule {
    @Binds
    @IntoMap
    @LazyClassKey(TextUIWhiteboardItem::class)
    fun bindsTextWhiteboardItemMapper(impl: TextWhiteboardItemMapper): WhiteboardItemMapper<*>

    @Binds
    @IntoMap
    @LazyClassKey(TextUIWhiteboardItem::class)
    fun bindsTextUIWhiteboardItemMapper(impl: TextUIWhiteboardItemMapper): UIWhiteboardItemMapper<*>

    @Binds
    @IntoMap
    @LazyClassKey(PictureUIWhiteboardItem::class)
    fun bindsPictureWhiteboardItemMapper(impl: PictureWhiteboardItemMapper): WhiteboardItemMapper<*>

    @Binds
    @IntoMap
    @LazyClassKey(PictureUIWhiteboardItem::class)
    fun bindsPictureUIWhiteboardItemMapper(impl: PictureUIWhiteboardItemMapper): UIWhiteboardItemMapper<*>
}