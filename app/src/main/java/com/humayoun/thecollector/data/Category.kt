package com.humayoun.thecollector.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "category")
data class Category (
        @PrimaryKey
        val name: String
//        @PrimaryKey(autoGenerate = true)
//        val categoryId: Int = 0
)