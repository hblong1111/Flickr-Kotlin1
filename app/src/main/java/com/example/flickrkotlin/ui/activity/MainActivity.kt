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


class MainActivity : ActivityNavigationBase<ActivityMainBinding>(), Callback<FlickrResult> {
    private lateinit var photoViewModel: PhotoViewModel


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvError.setOnClickListener {
            it.visibility = View.INVISIBLE
        }
        photoViewModel = ViewModelProvider(this).get(PhotoViewModel::class.java)

        getListPhoto()

    }

    private fun getListPhoto() {
        FlickrRetrofit.flickrService.getResult(ConverterAPI.getOptionCallResult(500, 1))
            .enqueue(this)
    }

    override fun onResponse(call: Call<FlickrResult>, response: Response<FlickrResult>) {
        val result: List<FlickrResult.Photos.Photo> =
            when (response.isSuccessful) {
                true -> response.body()?.photos?.photo!!
                else -> listOf()
            }
        photoViewModel.photos.postValue(result)

    }

    override fun onFailure(call: Call<FlickrResult>, t: Throwable) {
        binding.tvError.text = t.message
        binding.tvError.visibility = View.VISIBLE
    }
}