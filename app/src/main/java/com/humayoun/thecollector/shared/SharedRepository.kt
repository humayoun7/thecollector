package com.humayoun.thecollector.shared

import android.content.Context
import com.humayoun.thecollector.data.CollectorDatabase
import com.humayoun.thecollector.data.category.Category
import com.humayoun.thecollector.data.item.Item
import kotlinx.coroutines.*

class SharedRepository(context: Context) {

    private var collectorDatabase: CollectorDatabase? = CollectorDatabase.getCollectorDatabase(context)

    companion object {
        @Volatile
        private var INSTANCE: SharedRepository? = null

        fun getInstance(context: Context): SharedRepository {
            if(INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = SharedRepository(context)
                }
            }
            return INSTANCE!!
        }
    }


    //region category
    fun getAllCategories(): List<Category> = runBlocking {
        val list =  async { collectorDatabase?.categoryDao()?.getAll()  }
        return@runBlocking list.await() ?: listOf<Category>()
    }

    fun getAllItemsForCategory(name: String): List<Item> = runBlocking {
        val list =  async { collectorDatabase?.ItemDao()?.getItemsForCategory(name)  }
        return@runBlocking list.await() ?: listOf<Item>()
    }
    //endregion


    //region item
    fun insertItem(item: Item) {
        CoroutineScope(Dispatchers.IO).launch {
            collectorDatabase?.ItemDao()?.insertItem(item)
        }
    }

    fun insertCategory(category: Category) {
        CoroutineScope(Dispatchers.IO).launch {
            collectorDatabase?.categoryDao()?.insertCategory(category)
        }
    }
    //endregion

}