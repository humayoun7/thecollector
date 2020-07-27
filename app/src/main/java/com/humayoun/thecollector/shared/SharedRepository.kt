package com.humayoun.thecollector.shared

import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.humayoun.thecollector.data.CollectorDatabase
import com.humayoun.thecollector.data.category.Category
import com.humayoun.thecollector.data.item.Item
import com.humayoun.thecollector.ui.category.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.coroutines.*
import java.util.*

class SharedRepository(context: Context) {

    private var collectorDatabase: CollectorDatabase

    init {
        collectorDatabase = CollectorDatabase.getCollectorDatabase(context)
    }

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

//    fun getAllCategories(): List<Category> {
//        var list: List<Category>? = null
//        CoroutineScope(Dispatchers.IO).launch {
//            list = async { collectorDatabase.categoryDao().getAll()  }
//            list.await()
//        }
//
//        runBlocking {
//
//        }
//
//        return list
//    }
    fun getAllCategories(): List<Category> = runBlocking {
        val list =  async { collectorDatabase.categoryDao().getAll()  }
        return@runBlocking list.await()
    }

    fun getAllItemsForCategory(name: String): List<Item> = runBlocking {
        val list =  async { collectorDatabase.ItemDao().getItemsForCategory(name)  }
        return@runBlocking list.await()
    }

    fun insertItem(item: Item) {
        CoroutineScope(Dispatchers.IO).launch {
            collectorDatabase.ItemDao().insertItem(item)
        }
    }

    fun insertCategory(category: Category) {
        CoroutineScope(Dispatchers.IO).launch {
            collectorDatabase.categoryDao().insertCategory(category)
        }
    }

}