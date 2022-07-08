package com.example.flickrkotlin.ui.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.api.ConverterAPI
import com.example.api.FlickrResult
import com.example.api.FlickrRetrofit
import com.example.flickrkotlin.R
import com.example.flickrkotlin.databinding.ActivityMainBinding
import com.example.flickrkotlin.view_model.PhotoViewModel
import com.longhb.base.ActivityNavigationBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ActivityNavigationBase<ActivityMainBinding>() {
    private lateinit var photoViewModel: PhotoViewModel


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvError.setOnClickListener {
            it.visibility = View.INVISIBLE
        }
        photoViewModel = ViewModelProvider(this)[PhotoViewModel::class.java]

        photoViewModel.getListPhoto()
    }

}