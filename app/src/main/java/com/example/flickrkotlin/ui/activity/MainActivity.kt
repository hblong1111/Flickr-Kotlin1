package com.example.flickrkotlin.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.flickrkotlin.R
import com.example.flickrkotlin.databinding.ActivityMainBinding
import com.example.flickrkotlin.view_model.PhotoViewModel
import com.longhb.base.ActivityNavigationBase


class MainActivity : ActivityNavigationBase<ActivityMainBinding>() {
    private lateinit var photoViewModel: PhotoViewModel


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoViewModel = ViewModelProvider(this)[PhotoViewModel::class.java]

        if (photoViewModel.photos.isEmpty()) {
            photoViewModel.getListPhoto()
        }

    }

}