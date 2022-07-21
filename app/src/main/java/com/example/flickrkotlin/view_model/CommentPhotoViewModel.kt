package com.example.flickrkotlin.view_model

import androidx.lifecycle.ViewModel
import com.example.flickrkotlin.adapter.CommentsAdapter

class CommentPhotoViewModel : ViewModel() {

    private var commentAdapter: CommentsAdapter? = null

    fun getCommentAdapter(): CommentsAdapter {
        if (commentAdapter == null) {
            commentAdapter = CommentsAdapter()
        }
        return commentAdapter!!
    }
}