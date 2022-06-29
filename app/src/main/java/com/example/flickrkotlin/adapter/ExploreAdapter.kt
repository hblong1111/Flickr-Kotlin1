package com.example.flickrkotlin.adapter

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.api.FlickrResult
import com.example.flickrkotlin.LoadMoreCallback
import com.example.flickrkotlin.databinding.ItemExploreBinding

class ExploreAdapter constructor(var callback: LoadMoreCallback) :
    RecyclerView.Adapter<ExploreAdapter.ViewHolder>(), RequestListener<Drawable> {

    private var data: ArrayList<FlickrResult.Photos.Photo> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExploreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.binding.imageView).load(data[position].urlM).addListener(this)
            .into(holder.binding.imageView)
//        holder.bindData(photo = data[position])
//        if (position == itemCount - 1) {
//            callback.loadMore(itemCount)
//        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    fun setData(newData: ArrayList<FlickrResult.Photos.Photo>) {

        val oldData = ArrayList(data)
        data.addAll(newData)
        val diffUtils = DiffUtil.calculateDiff(PhotoDiffUtils(oldData, data))
        diffUtils.dispatchUpdatesTo(this)
    }

    fun addItem(item: FlickrResult.Photos.Photo) {
        val oldData = ArrayList(data)
        data.add(item)
        val diffUtils = DiffUtil.calculateDiff(PhotoDiffUtils(oldData, data))
        diffUtils.dispatchUpdatesTo(this)
    }

    class ViewHolder(val binding: ItemExploreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(photo: FlickrResult.Photos.Photo) {
            binding.photo = photo
        }
    }

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        callback.loadMore(itemCount - 1)
        return false
    }
}