package com.any.some.feature.whiteboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.any.some.domain.repository.WhiteboardItemDataRepository
import com.any.some.domain.usecase.InsertWhiteboardItemUseCase
import com.any.some.domain.usecase.TransformWhiteboardItemDataUseCase
import com.any.some.presentation.WhiteboardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WhiteboardManagerViewModel @Inject constructor(
    private val whiteboardItemDataRepository: WhiteboardItemDataRepository,
    private val insertWhiteboardItemUseCase: InsertWhiteboardItemUseCase,
    private val transformWhiteboardItemDataUseCase: TransformWhiteboardItemDataUseCase
) : ViewModel() {

    val items = whiteboardItemDataRepository.get().map { items ->
        items.map { transformWhiteboardItemDataUseCase(it) }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun insertWhiteboardItem(item: WhiteboardItem<*>) {
        viewModelScope.launch {
            insertWhiteboardItemUseCase(item)
        }
    }
}