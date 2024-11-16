package com.any.some.feature.whiteboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WhiteboardManagerViewModel @Inject constructor(
    private val whiteboardItemManager: WhiteboardItemManager
) : ViewModel() {

    val items = whiteboardItemManager
        .items
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun insertWhiteboardItem(item: WhiteboardItem<*>) {
        viewModelScope.launch { whiteboardItemManager.insert(item) }
    }
}