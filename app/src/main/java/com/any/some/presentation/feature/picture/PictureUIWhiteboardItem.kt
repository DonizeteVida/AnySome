package com.any.some.presentation.feature.picture

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.any.some.domain.model.WhiteboardItemType
import com.any.some.presentation.compose.permission.PermissionRequester
import com.any.some.presentation.model.UIWhiteboardItem
import com.any.some.ui.theme.AnySomeTheme

class PictureUIWhiteboardItem(
    id: Long = 0,
    type: WhiteboardItemType = WhiteboardItemType.PICTURE,
    body: String = "",
    offset: IntOffset = IntOffset.Zero,
    size: DpSize = DpSize(150.dp, 150.dp)
) : UIWhiteboardItem<String>(
    id,
    type,
    body,
    offset.x,
    offset.y,
    size.width.value,
    size.height.value
) {
    @Composable
    override fun WhiteboardItemContent() {
        PermissionRequester(android.Manifest.permission.CAMERA) {
            PictureWhiteboardItem(this)
        }
    }
}

@Composable
private fun PictureWhiteboardItem(state: PictureUIWhiteboardItem) {
    Column(
        Modifier
            .size(state.size)
            .background(Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.body.isEmpty()) {
            Icon(Icons.Default.BrokenImage, contentDescription = null)
        }
    }
}

@Preview
@Composable
private fun PrevPictureWhiteboardItem() {
    AnySomeTheme {
        PermissionRequester(android.Manifest.permission.CAMERA) {
            PictureWhiteboardItem(PictureUIWhiteboardItem())
        }
    }
}