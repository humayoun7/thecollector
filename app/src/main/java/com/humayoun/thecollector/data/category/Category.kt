package com.humayoun.thecollector.data.category

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "category")
data class Category (
        @PrimaryKey
        val name: String
)

//region extension
fun Category.existsIn(categories: List<Category>): Boolean {
       for (category in categories) {
               if(this.name.toLowerCase() == category.name.toLowerCase()) {
                       return true
               }
       }
        return false
}
//endregion