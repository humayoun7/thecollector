package com.humayoun.thecollector.Utils

import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity


object Utils {
    fun setActionBar(activity: Activity, title: String, displayBackButton: Boolean) {
        (activity as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(displayBackButton)
            supportActionBar?.title = title
        }
    }

    fun getRealPathFromURI(context: Context, uri: Uri): String? {
        val cursor: Cursor? = context.getContentResolver().query(uri, null, null, null, null)
        cursor?.let {
            cursor.moveToFirst()
            val idx: Int = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return cursor.getString(idx)
        }
        return null
    }
}