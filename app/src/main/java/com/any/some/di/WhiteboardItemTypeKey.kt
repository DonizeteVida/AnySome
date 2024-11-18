package com.any.some.di

import com.any.some.domain.model.WhiteboardItemType
import dagger.MapKey

@MapKey
annotation class WhiteboardItemTypeKey(
    val value: WhiteboardItemType
)