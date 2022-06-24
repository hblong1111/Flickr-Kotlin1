package com.example.flickrkotlin.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.FlickrResult


class PhotoViewModel : ViewModel() {
    val photos: MutableLiveData<List<FlickrResult.Photos.Photo>> = MutableLiveData()
}