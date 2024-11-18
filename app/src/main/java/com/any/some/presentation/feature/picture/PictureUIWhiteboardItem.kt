package com.any.some.presentation.feature.picture

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.any.some.MainFileProvider
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
    val context = LocalContext.current
    var nextUri by remember { mutableStateOf(MainFileProvider.generateTempFileUri(context)) }
    var currentUri by remember { mutableStateOf(Uri.EMPTY) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { taken ->
        if (!taken) return@rememberLauncherForActivityResult
        currentUri = nextUri
        nextUri = MainFileProvider.generateTempFileUri(context)
    }

    Column(
        Modifier
            .size(state.size)
            .background(Color.Red),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (currentUri != Uri.EMPTY) {
            AsyncImage(currentUri, contentDescription = null)
        }
        if (state.body.isEmpty()) {
//            Icon(Icons.Default.BrokenImage, contentDescription = null)
            IconButton(onClick = {
                launcher.launch(nextUri)
            }) {
                Text("Take a new photo")
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