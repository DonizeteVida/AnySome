package com.any.some

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class MainFileProvider : FileProvider() {
    companion object {
        private const val AUTHORITY = "com.any.some.main.file.provider"
        private const val TEMPORARY_FILES_FOLDER = "temporary"
        private const val PERMANENT_FILES_FOLDER = "permanent"

        @OptIn(ExperimentalUuidApi::class)
        private val randomFileName: String
            get() = Uuid.random().toString().replace("-", "_")

        fun generateTempFileUri(context: Context): Uri {
            val folder = File(context.cacheDir, TEMPORARY_FILES_FOLDER).also(File::mkdirs)
            val file = File(folder, randomFileName).also(File::createNewFile)
            return getUriForFile(context, AUTHORITY, file)
        }

        fun generatePermanentFileUri(context: Context): Uri {
            val folder = File(context.filesDir, PERMANENT_FILES_FOLDER).also(File::mkdirs)
            val file = File(folder, randomFileName).also(File::createNewFile)
            return getUriForFile(context, AUTHORITY, file)
        }
    }
}