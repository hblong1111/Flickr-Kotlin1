package com.example.flickrkotlin.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.api.FlickrResult
import com.example.flickrkotlin.R
import com.example.flickrkotlin.adapter.ExploreAdapter
import com.example.flickrkotlin.databinding.FragmentExploreBinding
import com.example.flickrkotlin.view_model.PhotoViewModel
import com.longhb.base.FragmentBase

class ExploreFragment : FragmentBase<FragmentExploreBinding>() {
    private lateinit var photoViewModel: PhotoViewModel
    private lateinit var data: ArrayList<FlickrResult.Photos.Photo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoViewModel = ViewModelProvider(requireActivity())[PhotoViewModel::class.java]
        data = ArrayList()

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_explore
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = ExploreAdapter()

        binding.rcv.adapter = adapter

        photoViewModel.photos.observe(requireActivity(), Observer {
            data.addAll(it)
            adapter.setData(ArrayList(data))
        })
    }


}