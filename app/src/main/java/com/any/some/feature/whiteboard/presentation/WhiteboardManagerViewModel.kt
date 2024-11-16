package com.any.some.feature.whiteboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.any.some.domain.usecase.InsertWhiteboardItemUseCase
import com.any.some.presentation.WhiteboardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WhiteboardManagerViewModel @Inject constructor(
    private val insertWhiteboardItemUseCase: InsertWhiteboardItemUseCase
) : ViewModel() {

    fun insertWhiteboardItem(item: WhiteboardItem<*>) {
        viewModelScope.launch {
            insertWhiteboardItemUseCase(item)
        }
    }
}