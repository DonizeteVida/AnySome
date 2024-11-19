package com.any.some.di

import com.any.some.presentation.model.UIWhiteboardItem
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class WhiteboardItemMapperKey(
    val value: KClass<out UIWhiteboardItem<*>>
)
