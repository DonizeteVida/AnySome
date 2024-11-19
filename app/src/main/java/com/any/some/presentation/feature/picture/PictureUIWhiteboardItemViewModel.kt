package com.any.some.presentation.feature.picture

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.any.some.MainFileProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class PictureUIWhiteboardItemViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val contentResolver: ContentResolver
) : ViewModel() {
    fun transferURI(state: PictureUIWhiteboardItem, uri: Uri) {
        val newUri = MainFileProvider.generatePermanentFileUri(context)
        contentResolver.openInputStream(uri)?.use { input ->
            contentResolver.openOutputStream(newUri)?.use(input::copyTo)
        }
        state.body = "$newUri"
    }
}