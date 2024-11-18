package com.any.some.presentation.whiteboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.any.some.presentation.model.UIWhiteboardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WhiteboardManagerViewModel @Inject constructor(
    private val uiWhiteboardItemManager: UIWhiteboardItemManager
) : ViewModel() {

    val items = uiWhiteboardItemManager
        .items
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun insertWhiteboardItem(item: UIWhiteboardItem<*>) {
        viewModelScope.launch {
            uiWhiteboardItemManager.insert(item)
        }
    }

    suspend fun updateUIWhiteboardItem(item: UIWhiteboardItem<*>) {
        uiWhiteboardItemManager.insert(item)
    }
}