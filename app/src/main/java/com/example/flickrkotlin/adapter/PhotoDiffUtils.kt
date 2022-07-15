package com.example.flickrkotlin.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.api.model.FlickrResult

class PhotoDiffUtils constructor(
    var oldData: ArrayList<FlickrResult.Photos.Photo>,
    var newData: ArrayList<FlickrResult.Photos.Photo>
) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldData.size
    }

    override fun getNewListSize(): Int {
        return newData.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPhoto = oldData[oldItemPosition]
        val newPhoto = newData[newItemPosition]
        return oldPhoto.equals(newPhoto)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPhoto = oldData[oldItemPosition]
        val newPhoto = newData[newItemPosition]
        return true
    }
}