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
import com.example.flickrkotlin.adapter.CommentsAdapter
import com.example.flickrkotlin.databinding.FragmentCommentDialogBinding
import com.example.flickrkotlin.view_model.CommentPhotoViewModel
import com.example.flickrkotlin.view_model.PhotoViewModel
import com.longhb.base.DialogFragmentBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentDialogFragment : DialogFragmentBase<FragmentCommentDialogBinding>() {
    private lateinit var viewModel: CommentPhotoViewModel
    private lateinit var adapter: CommentsAdapter
    override fun getLayoutId(): Int {
        return R.layout.fragment_comment_dialog
    }

    override fun setAnimationCustom(): Int {
        return R.style.WindowAnimationSlideVertical
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProvider(this)[CommentPhotoViewModel::class.java]

        adapter = viewModel.getCommentAdapter()
    }

    override fun onCustomCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        super.onCustomCreateView(inflater, container, savedInstanceState)

        getListComment()

        binding.rcv.adapter = adapter
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
                if (response.isSuccessful) {
                    if (response.body()?.comments?.comment?.size != 0) {
                        adapter.setDataNew(ArrayList(response.body()?.comments?.comment))
                    }
                }
            }

            override fun onFailure(call: Call<CommentsPhotoResult>, t: Throwable) {
                Log.d("longhb", "CommentDialogFragment.onFailure: ${t.printStackTrace()}")
            }

        })
    }
}