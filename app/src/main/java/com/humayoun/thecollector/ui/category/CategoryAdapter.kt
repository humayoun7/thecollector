package com.humayoun.thecollector.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.humayoun.thecollector.R
import com.humayoun.thecollector.data.category.Category

class CategoryAdapter (private val categories: List<Category>,
                       private val clickListner: CategoryClickListner
):
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTV = itemView.findViewById<TextView>(R.id.tv_category_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_category, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]

        holder.categoryTV?.text = category.name
        
        // to handle click events
        holder.itemView.setOnClickListener { 
            clickListner.onCategoryItemClick(category)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    interface CategoryClickListner {
        fun onCategoryItemClick(category: Category)
    }
}