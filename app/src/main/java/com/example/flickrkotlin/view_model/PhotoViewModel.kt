package com.example.flickrkotlin.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.FlickrResult
import com.example.flickrkotlin.repository.PhotoRepository
import retrofit2.Call


class PhotoViewModel : ViewModel() {
    val photosLiveData: MutableLiveData<List<FlickrResult.Photos.Photo>> = MutableLiveData()
    val isLoadData: MutableLiveData<Boolean> = MutableLiveData()
    val photos: ArrayList<FlickrResult.Photos.Photo> = ArrayList()

    var positionSelect = 0

    fun getListPhoto() {
        isLoadData.postValue(true)
        PhotoRepository.getPhotoExplore(photos.size,
            object : PhotoRepository.GetPhotoExploreCallBack {
                override fun onSuccess(data: ArrayList<FlickrResult.Photos.Photo>) {
                    photos.addAll(data)
                    Log.d("longhb", "PhotoViewModel.onSuccess: ${photos.size}")
                    photosLiveData.postValue(photos)
                    isLoadData.postValue(false)
                }

                override fun onFailure(call: Call<FlickrResult>, t: Throwable) {
                    Log.d("longhb", "PhotoViewModel.onFailure: ${t.message}")
                    isLoadData.postValue(false)
                }

            })
    }
}