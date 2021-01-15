package com.example.statussaver.`package`

import android.content.Context
import android.os.Environment
import android.util.Log
import com.example.statussaver.utils.isImageFile
import java.io.File

private const val TYPE_VIDEO = 12
private const val TYPE_IMAGE = 13
private const val TYPE_SAVED = 15

class FileRepository(private val context: Context) {


    private val parentDir =
        File(Environment.getExternalStorageDirectory().toString() + WHATSAPP_STATUSES_LOCATION)

    companion object {
        const val WHATSAPP_STATUSES_LOCATION = "/WhatsApp/Media/.Statuses"

    }

    fun getImage(): List<File> {
        Log.e("GUHAN", parentDir.toString())
        val files = parentDir.listFiles()
        val fetchedFiles: ArrayList<File> = ArrayList()
        files?.let {
            for (file in it) {
                if (isImageFile(context, file.path)) {
                    fetchedFiles.add(file)
                }
            }
        }
        return fetchedFiles
    }
}