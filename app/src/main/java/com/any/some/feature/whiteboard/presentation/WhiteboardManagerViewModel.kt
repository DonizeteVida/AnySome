package com.any.some.feature.whiteboard.presentation

import androidx.lifecycle.ViewModel
import com.any.some.domain.repository.WhiteboardItemDataRepository
import com.any.some.presentation.WhiteboardItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WhiteboardManagerViewModel @Inject constructor(
    private val whiteboardItemDataRepository: WhiteboardItemDataRepository
) : ViewModel() {

    fun insertWhiteboardItem(item: WhiteboardItem<*>) {

    }
}