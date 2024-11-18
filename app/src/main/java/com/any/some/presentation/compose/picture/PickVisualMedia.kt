package com.any.some.presentation.compose.picture

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun PickVisualMedia(
    mediaType: ActivityResultContracts.PickVisualMedia.VisualMediaType = ActivityResultContracts.PickVisualMedia.ImageOnly,
    onPicture: (Uri) -> Unit,
    content: @Composable (pickVisualMedia: () -> Unit) -> Unit
) {
    var uri by remember(onPicture) { mutableStateOf(Uri.EMPTY) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        uri = it ?: uri
    }

    LaunchedEffect(onPicture, uri) {
        if (uri == Uri.EMPTY) return@LaunchedEffect
        onPicture(uri)
    }

    content { launcher.launch(PickVisualMediaRequest(mediaType)) }
}