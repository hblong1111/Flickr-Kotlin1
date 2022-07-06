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
import com.example.flickrkotlin.EndlessRecyclerViewScrollListener
import com.example.flickrkotlin.databinding.ItemExploreBinding

class ExploreAdapter :
    RecyclerView.Adapter<ExploreAdapter.ViewHolder>() {

    private var data: ArrayList<FlickrResult.Photos.Photo> = ArrayList()
    private var dataDisplay: ArrayList<FlickrResult.Photos.Photo> = ArrayList()

    private var width = 0

    private val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                setDataDisplay(ArrayList(data.take(itemCount + 1)))
            }
        })
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExploreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val photo = dataDisplay[position]

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

    override fun getItemCount(): Int {
        return dataDisplay.size
    }


    fun setDataDisplay(newData: ArrayList<FlickrResult.Photos.Photo>) {
        val diffUtils = DiffUtil.calculateDiff(PhotoDiffUtils(dataDisplay, newData))
        diffUtils.dispatchUpdatesTo(this)

        dataDisplay.clear()
        dataDisplay.addAll(newData)
    }

    fun setData(data: java.util.ArrayList<FlickrResult.Photos.Photo>) {
        this.data.clear()
        this.data.addAll(data)
        if (dataDisplay.isEmpty() && data.isNotEmpty()) {
            setDataDisplay(ArrayList(data.take(1)))
        }
    }


    class ViewHolder(val binding: ItemExploreBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(photo: FlickrResult.Photos.Photo) {
            binding.photo = photo
        }
    }

}