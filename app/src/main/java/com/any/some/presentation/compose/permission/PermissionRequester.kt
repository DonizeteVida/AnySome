package com.any.some.presentation.compose.permission

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequester(
    permission: String,
    content: @Composable () -> Unit
) {
    val state = rememberPermissionState(permission)
    if (state.status.isGranted.not()) {
        Button(onClick = { state.launchPermissionRequest() }) {
            Text("Please, grant camera permission")
        }
        return
    }
    content()
}