package com.humayoun.thecollector.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.humayoun.thecollector.data.category.Category
import com.humayoun.thecollector.data.category.CategoryDao
import com.humayoun.thecollector.data.item.Item
import com.humayoun.thecollector.data.item.ItemDao

@Database(entities = [Category::class, Item::class], version = 1, exportSchema = false)
abstract class CollectorDatabase: RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun ItemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: CollectorDatabase? = null
        private var databaseName = "collector.db"

        fun getCollectorDatabase(context: Context): CollectorDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CollectorDatabase::class.java,
                        databaseName
                    ).build()
                }
            }
            // todo change this to elvis operator
            return INSTANCE!!
        }
    }
}