package com.example.flickrkotlin.helper

import android.content.Context
import android.content.Intent
import android.os.Environment
import com.example.flickrkotlin.R
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.net.URL


object ImageDownloadHelper {
    fun saveImageIntoDevice(context: Context, urlPath: String): String {
        val url = URL(urlPath)
        val input: InputStream = url.openStream()
        input.use { input ->
            //The sdcard directory e.g. '/sdcard' can be used directly, or
            //more safely abstracted with getExternalStorageDirectory()
            val storagePath: File =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val fileName =
                context.getString(R.string.app_name) + " - " + System.currentTimeMillis() + ".png"
            val fileSave = File(storagePath, fileName)
            val output: OutputStream = FileOutputStream(fileSave)
            output.use { output ->
                val buffer = ByteArray(1024)
                var bytesRead = 0
                while (input.read(buffer, 0, buffer.size).also { bytesRead = it } >= 0) {
                    output.write(buffer, 0, bytesRead)
                }

                return fileSave.absolutePath
            }
        }

        return ""
    }

    fun shareImageUrl(context: Context, urlPath: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
        intent.putExtra(Intent.EXTRA_TEXT, urlPath)
        context.startActivity(intent)
    }

}