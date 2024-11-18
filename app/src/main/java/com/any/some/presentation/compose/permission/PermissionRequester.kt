package com.any.some.presentation.compose.permission

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
        IconButton(onClick = {
            state.launchPermissionRequest()
        }) {
            Icon(Icons.Default.AdminPanelSettings, contentDescription = "")
        }
        return
    }
    content()
}