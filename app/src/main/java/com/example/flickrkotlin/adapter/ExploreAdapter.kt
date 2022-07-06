package com.example.flickrkotlin.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.api.FlickrResult
import com.example.flickrkotlin.databinding.ItemExploreBinding
import com.longhb.base.adapter.AdapterRecyclerViewBase

class ExploreAdapter(var callback: ExploreAdapterCallback) :
    AdapterRecyclerViewBase<FlickrResult.Photos.Photo, ExploreAdapter.ViewHolder>() {

    override var layoutManager: RecyclerView.LayoutManager =
        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

    private var width = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExploreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val photo = this.dataShow[position]

        if (width != 0) {
            val heightDraw = width * photo.getHeight() / photo.getWidth()
            holder.binding.imageView.layoutParams.height = heightDraw
            Glide.with(holder.binding.imageView).load(photo.getUrl())
                .into(holder.binding.imageView)
        } else {
            holder.binding.imageView.post(kotlinx.coroutines.Runnable {
                width = holder.binding.imageView.measuredWidth
                val heightDraw = width * photo.getHeight() / photo.getWidth()
                holder.binding.imageView.layoutParams.height = heightDraw
                Glide.with(holder.binding.imageView).load(photo.getUrl())
                    .into(holder.binding.imageView)
            })
        }


    }


    class ViewHolder(val binding: ItemExploreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(photo: FlickrResult.Photos.Photo) {
            binding.photo = photo
        }
    }


    override fun loadComplete() {
        callback.loadComplete()
    }

    override fun areItemsTheSame(
        oldItem: FlickrResult.Photos.Photo,
        newItem: FlickrResult.Photos.Photo
    ): Boolean {
        return oldItem.equals(newItem)
    }

    override fun areContentsTheSame(
        oldItem: FlickrResult.Photos.Photo,
        newItem: FlickrResult.Photos.Photo
    ): Boolean {
        return true
    }


    interface ExploreAdapterCallback {
        fun loadComplete()
    }


}