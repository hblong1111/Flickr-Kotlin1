package com.example.flickrkotlin.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.FlickrResult
import com.example.flickrkotlin.repository.PhotoRepository
import retrofit2.Call


class PhotoViewModel : ViewModel() {
    val photosLiveData: MutableLiveData<List<FlickrResult.Photos.Photo>> = MutableLiveData()
    val photos: ArrayList<FlickrResult.Photos.Photo> = ArrayList()

    fun getListPhoto() {
        Log.d("longhb", "PhotoViewModel.getListPhoto: ")
        PhotoRepository.getPhotoExplore(object : PhotoRepository.GetPhotoExploreCallBack {
            override fun onSuccess(data: ArrayList<FlickrResult.Photos.Photo>) {
                Log.d("longhb", "PhotoViewModel.onSuccess: ")
                photos.addAll(data)
                photosLiveData.postValue(photos)
            }

            override fun onFailure(call: Call<FlickrResult>, t: Throwable) {
                Log.d("longhb", "PhotoViewModel.onFailure: ${t.message}")
            }

        })
    }
}