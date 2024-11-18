package com.any.some.presentation.feature.picture

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.any.some.domain.model.WhiteboardItemType
import com.any.some.presentation.compose.permission.PermissionRequester
import com.any.some.presentation.compose.picture.PickVisualMedia
import com.any.some.presentation.compose.picture.TakePicture
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
        PictureWhiteboardItem(this)
    }
}

@Composable
private fun PictureWhiteboardItem(state: PictureUIWhiteboardItem) {
    Column(
        Modifier.size(state.size),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (state.body.isNotEmpty()) {
            AsyncImage(
                Uri.parse(state.body),
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .weight(1F)
            )
        }
        Row {
            PickVisualMedia(
                onPicture = { uri -> state.body = "$uri" }
            ) { takePicture ->
                IconButton(onClick = takePicture) {
                    Icon(
                        Icons.Default.ImageSearch,
                        contentDescription = "Take a picture using your gallery"
                    )
                }
            }

            TakePicture(
                onPicture = { uri -> state.body = "$uri" }
            ) { takePicture ->
                IconButton(onClick = takePicture) {
                    Icon(
                        Icons.Default.Camera,
                        contentDescription = "Take a picture using your camera"
                    )
                }
            }
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