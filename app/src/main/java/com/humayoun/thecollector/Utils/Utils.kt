package com.humayoun.thecollector.Utils

import android.app.Activity
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.humayoun.thecollector.data.category.Category
import java.util.*


object Utils {
    fun setActionBar(activity: Activity, title: String, displayBackButton: Boolean) {
        (activity as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(displayBackButton)
            supportActionBar?.title = title
        }
    }

    fun isValidInput(input: String): Boolean {
        return !input.isEmpty()
    }

    fun showError(context: Context, error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }
}