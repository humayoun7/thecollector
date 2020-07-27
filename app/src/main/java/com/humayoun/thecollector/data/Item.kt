package com.humayoun.thecollector.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity (tableName = "items")
@ForeignKey(entity = Category::class, parentColumns = ["name"], childColumns = ["categoryName"], onDelete = CASCADE)
data class Item(
    val title: String,
    val description: String,
    val rating: Float,
    val ImageUri: String = "",
    val categoryName: String,
    @PrimaryKey (autoGenerate = true)
    val itemId: Int = 0
)