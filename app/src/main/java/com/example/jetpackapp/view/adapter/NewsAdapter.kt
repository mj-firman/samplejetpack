package com.example.jetpackapp.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpackapp.R
import com.example.jetpackapp.model.NewsResponse
import kotlinx.android.synthetic.main.item_data.view.*

abstract class NewsAdapter(var context: Context?, private var itemsData: ArrayList<NewsResponse.NewsEntity>): RecyclerView.Adapter<NewsAdapter.DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(context).inflate(R.layout.item_data, parent, false))
    }

    override fun getItemCount(): Int {
        return itemsData.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = itemsData[position]
        holder.tvName.text = item.title
        Glide.with(context as Context).load(item.urlNewsImageRectangle).into(holder.ivProfile)
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.tvName
        val ivProfile: ImageView = itemView.ivProfile
    }
}