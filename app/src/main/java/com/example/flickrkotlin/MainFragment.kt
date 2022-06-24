package com.example.flickrkotlin

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.api.ConverterAPI
import com.example.api.FlickrResult
import com.example.api.FlickrRetrofit
import com.example.api.FlickrService
import com.example.flickrkotlin.databinding.FragmentMainBinding
import com.longhb.base.FragmentBase
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainFragment : FragmentBase<FragmentMainBinding>(), retrofit2.Callback<FlickrResult> {
    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FlickrRetrofit.flickrService.getResult(ConverterAPI.getOptionCallResult(1000, 1))
            .enqueue(this)
    }

    override fun onResponse(call: Call<FlickrResult>, response: Response<FlickrResult>) {
        Log.d("longhb", "MainFragment.onResponse: ${response.body()?.photos?.photo?.size}")
    }

    override fun onFailure(call: Call<FlickrResult>, t: Throwable) {
        Log.d("longhb", "MainFragment.onFailure: ${t.message}")
    }
}

