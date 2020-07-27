package com.humayoun.thecollector.data.category

import androidx.room.*
import com.humayoun.thecollector.data.category.Category

@Dao
interface CategoryDao {

    @Query("SELECT * FROM category")
    suspend fun getAll(): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category): Int

    @Query("DELETE from category")
    suspend fun deleteAllCategories(): Int
}