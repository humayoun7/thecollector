package com.humayoun.thecollector.ui.item

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.humayoun.thecollector.R
import com.humayoun.thecollector.data.item.Item

class ItemAdpater(
    private val context: Context,
    private val items: List<Item>,
    private val itemClickListner: ItemClickListner
): RecyclerView.Adapter<ItemAdpater.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTV = itemView.findViewById<TextView>(R.id.tv_item_title)
        val descTV = itemView.findViewById<TextView>(R.id.tv_item_description)
        val itemIV = itemView.findViewById<ImageView>(R.id.iv_item)
        val ratingBar = itemView.findViewById<RatingBar>(R.id.rb_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        val imageUri = Uri.parse(item.ImageUri)

        with(holder) {
            titleTV.text = item.title
            descTV.text = item.description
            ratingBar.rating = item.rating

            Glide.with(context)
                .load(imageUri)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemIV)
        }

        holder.itemView.setOnClickListener {
            itemClickListner.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface ItemClickListner {
        fun onItemClick(item: Item)
    }
}
