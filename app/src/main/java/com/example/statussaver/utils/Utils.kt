package com.example.statussaver.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap

fun isImageFile(context: Context, path: String): Boolean {
    val uri: Uri = Uri.parse(path)

    val mimeType: String?
    mimeType = if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
        val cr = context.contentResolver
        cr.getType(uri)
    } else {
        val fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri
            .toString())
        MimeTypeMap.getSingleton().getMimeTypeFromExtension(
            fileExtension.toLowerCase())
    }


    return mimeType != null && mimeType.startsWith("image")
}