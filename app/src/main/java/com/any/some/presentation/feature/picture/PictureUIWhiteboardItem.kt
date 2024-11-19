package com.any.some.presentation.feature.picture

import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.ImageSearch
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.any.some.presentation.compose.permission.PermissionRequester
import com.any.some.presentation.compose.picture.PickVisualMedia
import com.any.some.presentation.compose.picture.TakePicture
import com.any.some.presentation.model.UIWhiteboardItem
import com.any.some.ui.theme.AnySomeTheme

class PictureUIWhiteboardItem(
    id: Long = 0,
    body: String = "",
    offset: IntOffset = IntOffset.Zero,
    size: DpSize = DpSize(150.dp, 150.dp)
) : UIWhiteboardItem<String>(
    id,
    body,
    offset.x,
    offset.y,
    size.width.value,
    size.height.value
) {
    @Composable
    override fun Content() {
        PictureWhiteboardItem(this)
    }

    @Composable
    override fun Toolbar() {
        PictureWhiteboardItemToolbar(this)
    }
}

@Composable
private fun PictureWhiteboardItem(
    state: PictureUIWhiteboardItem
) {
    if (state.body.isNotEmpty()) {
        AsyncImage(
            Uri.parse(state.body),
            contentDescription = null,
            Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PictureWhiteboardItemToolbar(
    state: PictureUIWhiteboardItem
) {
    val onPicture = remember(state) { { uri: Uri -> state.body = "$uri" } }

    PickVisualMedia(
        onPicture = onPicture
    ) { takePicture ->
        IconButton(onClick = takePicture) {
            Icon(
                Icons.Default.ImageSearch,
                contentDescription = "Take a picture using your gallery"
            )
        }
    }

    TakePicture(
        onPicture = onPicture
    ) { takePicture ->
        IconButton(onClick = takePicture) {
            Icon(
                Icons.Default.Camera,
                contentDescription = "Take a picture using your camera"
            )
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