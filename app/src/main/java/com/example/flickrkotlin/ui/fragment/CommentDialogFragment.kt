package com.example.flickrkotlin.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.api.ConverterAPI
import com.example.api.FlickrRetrofit
import com.example.api.model.CommentsPhotoResult
import com.example.flickrkotlin.R
import com.example.flickrkotlin.databinding.FragmentCommentDialogBinding
import com.example.flickrkotlin.view_model.CommentPhotoViewModel
import com.example.flickrkotlin.view_model.PhotoViewModel
import com.longhb.base.DialogFragmentBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentDialogFragment : DialogFragmentBase<FragmentCommentDialogBinding>() {
    private lateinit var viewModel: CommentPhotoViewModel
    override fun getLayoutId(): Int {
        return R.layout.fragment_comment_dialog
    }

    override fun setAnimationCustom(): Int {
        return R.style.WindowAnimationSlideVertical
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProvider(this)[CommentPhotoViewModel::class.java]
    }

    override fun onCustomCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        super.onCustomCreateView(inflater, container, savedInstanceState)

        getListComment()
    }

    private fun getListComment() {

        val photoViewModel = ViewModelProvider(requireActivity())[PhotoViewModel::class.java]

        val photo = photoViewModel.getCurrentPhotoShowDetail()

        FlickrRetrofit.flickrService.getListCommentPhoto(
            ConverterAPI.getOptionCallListCommentPhoto(
                photo?.id!!
            )
        ).enqueue(object : Callback<CommentsPhotoResult> {
            override fun onResponse(
                call: Call<CommentsPhotoResult>,
                response: Response<CommentsPhotoResult>
            ) {
                Log.d(
                    "hblong",
                    "CommentDialogFragment.onResponse: ${response.body()?.comments?.comment?.size}"
                )
            }

            override fun onFailure(call: Call<CommentsPhotoResult>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}