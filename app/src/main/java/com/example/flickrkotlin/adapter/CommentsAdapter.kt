package com.example.flickrkotlin.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.model.CommentsPhotoResult
import com.example.flickrkotlin.databinding.ItemCommentBinding
import com.longhb.base.adapter.AdapterRecyclerViewBase

class CommentsAdapter :
    AdapterRecyclerViewBase<CommentsPhotoResult.Comments.Comment, CommentsAdapter.ViewHolder>() {

    override fun createLayoutManager(context: Context): RecyclerView.LayoutManager {
        return object : LinearLayoutManager(context) {

            override fun supportsPredictiveItemAnimations(): Boolean {
                return false
            }
        }
    }

    override fun loadComplete() {
        Log.d("hblong", "CommentsAdapter.loadComplete: end")
    }

    override fun areItemsTheSame(
        oldItem: CommentsPhotoResult.Comments.Comment,
        newItem: CommentsPhotoResult.Comments.Comment
    ): Boolean {
        return oldItem.equals(newItem)
    }

    override fun areContentsTheSame(
        oldItem: CommentsPhotoResult.Comments.Comment,
        newItem: CommentsPhotoResult.Comments.Comment
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(dataShow[position])

        calculatePaddingItem(holder.itemView, position)
    }

    private fun calculatePaddingItem(itemView: View, position: Int) {
        val base = 20
        val top: Int
        val bottom: Int

        when (position) {
            0 -> {
                top = 2 * base
                bottom = base / 2
            }
            data.size - 1 -> {
                top = base / 2
                bottom = 2 * base
            }
            else -> {
                top = base / 2
                bottom = base / 2
            }
        }
        itemView.updatePadding(base, top, base, bottom)
    }


    class ViewHolder(var binding: ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(comment: CommentsPhotoResult.Comments.Comment) {
            binding.comment = comment
        }
    }
}