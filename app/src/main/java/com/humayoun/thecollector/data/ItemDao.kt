package com.humayoun.thecollector.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {

    @Query ("SELECT * FROM items")
    suspend fun getAll(): List<Item>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Query ("SELECT * FROM items WHERE categoryName = :categoryName")
    suspend fun getItemsForCategory(categoryName: String): List<Item>

    @Query ("DELETE FROM items WHERE itemId = :itemId")
    suspend fun deleteItem(itemId: Int): Int
}