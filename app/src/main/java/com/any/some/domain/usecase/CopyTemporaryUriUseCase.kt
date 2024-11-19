package com.any.some.domain.usecase

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import com.any.some.MainFileProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CopyTemporaryUriUseCase @Inject constructor(
    @ApplicationContext private val context: Context,
    private val contentResolver: ContentResolver
) {
    suspend operator fun invoke(uri: Uri): Uri = withContext(Dispatchers.IO) {
        val newUri = MainFileProvider.generatePermanentFileUri(context)
        contentResolver.openInputStream(uri)?.use { input ->
            contentResolver.openOutputStream(newUri)?.use(input::copyTo)
        }
        newUri
    }
}