package com.example.flickrkotlin.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.flickrkotlin.R
import com.example.flickrkotlin.adapter.DetailAdapter
import com.example.flickrkotlin.databinding.FragmentImageDetailBinding
import com.example.flickrkotlin.view_model.PhotoViewModel
import com.longhb.base.FragmentBase

class ImageDetailFragment : FragmentBase<FragmentImageDetailBinding>() {

    private lateinit var photoViewModel: PhotoViewModel

    private lateinit var adapter: DetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoViewModel = ViewModelProvider(requireActivity())[PhotoViewModel::class.java]
        adapter = DetailAdapter(photoViewModel.photos)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_image_detail
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewpager.adapter = adapter
        binding.viewpager.setCurrentItem(photoViewModel.positionSelect, false)


        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                photoViewModel.positionSelect = position
            }
        })
    }
}