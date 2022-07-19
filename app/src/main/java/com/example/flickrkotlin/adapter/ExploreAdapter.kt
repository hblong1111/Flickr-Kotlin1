package com.example.flickrkotlin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.api.model.FlickrResult
import com.example.flickrkotlin.databinding.ItemExploreBinding
import com.longhb.base.adapter.AdapterRecyclerViewBase

class ExploreAdapter(var callback: ExploreAdapterCallback) :
    AdapterRecyclerViewBase<FlickrResult.Photos.Photo, ExploreAdapter.ViewHolder>() {

    private var width = 0

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        width = 0
        super.onAttachedToRecyclerView(recyclerView)
    }

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
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(holder.binding.imageView)
        } else {
            holder.binding.imageView.post(kotlinx.coroutines.Runnable {
                width = holder.binding.imageView.measuredWidth
                val heightDraw = width * photo.getHeight() / photo.getWidth()
                holder.binding.imageView.layoutParams.height = heightDraw
                Glide.with(holder.binding.imageView).load(photo.getUrl())
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(holder.binding.imageView)
            })
        }


        holder.binding.imageView.setOnClickListener {
            callback.onClickItem(it, holder.adapterPosition)
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
        fun onClickItem(view: View, position: Int)
    }

    override fun createLayoutManager(): RecyclerView.LayoutManager {
        return object : StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL) {
            override fun supportsPredictiveItemAnimations(): Boolean {
                return false
            }
        }
    }


}