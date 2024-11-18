package com.any.some.presentation.compose.picture

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.any.some.MainFileProvider
import com.any.some.presentation.compose.permission.PermissionRequester

@Composable
fun TakePicture(
    onPicture: (Uri) -> Unit,
    content: @Composable (takePicture: () -> Unit) -> Unit
) {
    val context = LocalContext.current
    var uri by remember { mutableStateOf(MainFileProvider.generateTempFileUri(context)) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { taken ->
        if (!taken) return@rememberLauncherForActivityResult
        onPicture(uri)
        uri = MainFileProvider.generateTempFileUri(context)
    }

    PermissionRequester(android.Manifest.permission.CAMERA) {
        content { launcher.launch(uri) }
    }
}