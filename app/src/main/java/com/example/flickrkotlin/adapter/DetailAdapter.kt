package com.example.flickrkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.FlickrResult
import com.example.flickrkotlin.databinding.ItemDetailBinding

class DetailAdapter(val data: ArrayList<FlickrResult.Photos.Photo>) :
    RecyclerView.Adapter<DetailAdapter.ViewHolder>() {


    class ViewHolder(val binding: ItemDetailBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = data[position]


        Glide.with(holder.itemView)
            .load(photo.getUrlHD())
            .thumbnail(
                Glide.with(holder.itemView)
                    .load(photo.getUrl())
            )
            .into(holder.binding.imv)


    }

    override fun getItemCount(): Int {
        return data.size
    }
}