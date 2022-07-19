package com.example.flickrkotlin.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.model.FlickrResult
import com.example.flickrkotlin.adapter.ExploreAdapter
import com.example.flickrkotlin.repository.PhotoRepository
import retrofit2.Call


class PhotoViewModel : ViewModel() {
    val photosLiveData: MutableLiveData<List<FlickrResult.Photos.Photo>> = MutableLiveData()
    val isLoadData: MutableLiveData<Boolean> = MutableLiveData()
    val positionFocus: MutableLiveData<Int> = MutableLiveData()
    val photos: ArrayList<FlickrResult.Photos.Photo> = ArrayList()


    private var exploreAdapter: ExploreAdapter? = null


    fun getExploreAdapter(callback: ExploreAdapter.ExploreAdapterCallback): ExploreAdapter {
        if (exploreAdapter == null) {
            exploreAdapter = ExploreAdapter(callback)
        } else {
            exploreAdapter?.callback = callback
        }
        return exploreAdapter!!
    }

    var positionSelect = 0

    fun getListPhoto() {
        isLoadData.postValue(true)
        PhotoRepository.getPhotoExplore(photos.size,
            object : PhotoRepository.GetPhotoExploreCallBack {
                override fun onSuccess(data: ArrayList<FlickrResult.Photos.Photo>) {
                    photos.addAll(data)
                    photosLiveData.postValue(photos)
                    isLoadData.postValue(false)
                }

                override fun onFailure(call: Call<FlickrResult>, t: Throwable) {
                    isLoadData.postValue(false)
                }
            }
        )
    }

    fun getCurrentPhotoShowDetail(): FlickrResult.Photos.Photo? {
        if (positionSelect >= 0 && positionSelect < photos.size) {
            return photos[positionSelect]
        }
        return null
    }
}