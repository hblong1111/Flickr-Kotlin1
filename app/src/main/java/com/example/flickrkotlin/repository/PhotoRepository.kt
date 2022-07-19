package com.example.flickrkotlin.repository

import android.util.Log
import com.example.api.ConverterAPI
import com.example.api.FlickrRetrofit
import com.example.api.model.FlickrResult
import com.example.flickrkotlin.utils.DateUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.ceil

object PhotoRepository {

    //get all photo explore
    private var totalPhotoOfPageExplore = 500f
    private var sizeIgnoreExplore = 0


    fun getPhotoExplore(totalPhoto: Int, callBack: GetPhotoExploreCallBack) {
        FlickrRetrofit.flickrService.getResult(
            ConverterAPI.getOptionCallResult(
                DateUtils.getDateString(
                    calculatorDayWithSize(totalPhoto)
                ), totalPhotoOfPageExplore.toInt(), 1
            )
        ).enqueue(object : Callback<FlickrResult> {
            override fun onResponse(
                call: Call<FlickrResult>,
                response: Response<FlickrResult>
            ) {
                if (response.isSuccessful) {
                    val size = response.body()?.photos?.photo?.size
                    if (response.code() == 200 && size != null && size != 0) {
                        callBack.onSuccess(response.body()?.photos?.photo!!)
                    } else {
                        sizeIgnoreExplore += totalPhotoOfPageExplore.toInt()
                        getPhotoExplore(totalPhoto, callBack)
                    }
                }
            }

            override fun onFailure(call: Call<FlickrResult>, t: Throwable) {
                callBack.onFailure(call, t)
            }
        })
    }

    fun calculatorDayWithSize(totalPhoto: Int): Int {
        return ceil((totalPhoto + sizeIgnoreExplore) / totalPhotoOfPageExplore).toInt()
    }


    interface GetPhotoExploreCallBack {
        fun onSuccess(data: ArrayList<FlickrResult.Photos.Photo>)
        fun onFailure(call: Call<FlickrResult>, t: Throwable)
    }
}