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
import com.example.jetpackapp.model.HeroResponse
import kotlinx.android.synthetic.main.item_data.view.*

abstract class HeroAdapter(var context: Context?, var listHero: List<HeroResponse>): RecyclerView.Adapter<HeroAdapter.DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInflater.from(context).inflate(R.layout.item_data, parent, false))
    }

    override fun getItemCount(): Int {
        return listHero.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val data = listHero[position]
        holder.tvName.text = data.name
        Glide.with(context as Context).load(data.imageurl).into(holder.ivProfile)
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.tvName
        val ivProfile: ImageView = itemView.ivProfile
    }
}