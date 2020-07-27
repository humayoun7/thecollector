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
}