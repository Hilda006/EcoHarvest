package com.example.ecoharvest.utils

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Locale

private const val FORMAT_FILENAME = "yyyy-MM-dd"
private val timeStamp = SimpleDateFormat(FORMAT_FILENAME, Locale.US).format(System.currentTimeMillis())

private fun getUri(context: Context) : Uri {
    var uri: Uri? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/MyCamera/")
            put(MediaStore.MediaColumns.DISPLAY_NAME, "$timeStamp.jpg")
        }
        uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    }
    return uri?: getUri(context)


}

fun createTimeFile(context: Context) : File {

    val storage: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(timeStamp, ".jpg", storage)
}

@SuppressLint("Recycle")
fun uriToFile(uri: Uri, context: Context) : File{
    val contentResolver = context.contentResolver
    val file = createTimeFile(context)
    val inputStream = contentResolver.openInputStream(uri) as InputStream
    val outputStream = FileOutputStream(file)
    val buffer = ByteArray(1024)
    var len: Int
    while (inputStream.read(buffer).also {
            len = it } >= 0) outputStream.write(buffer, 0, len)
    outputStream.close()
    inputStream.close()
    return file

}