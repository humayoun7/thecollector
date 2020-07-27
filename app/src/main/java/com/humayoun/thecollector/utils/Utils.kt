package com.humayoun.thecollector.utils

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


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