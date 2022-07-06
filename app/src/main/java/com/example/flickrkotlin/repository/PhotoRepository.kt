package com.example.flickrkotlin.repository

import com.example.api.ConverterAPI
import com.example.api.FlickrResult
import com.example.api.FlickrRetrofit
import com.example.flickrkotlin.utils.DateUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.ceil

object PhotoRepository {
    private var totalPhotoOfPage = 500f


    fun getPhotoExplore(totalPhoto: Int, callBack: GetPhotoExploreCallBack) {
        FlickrRetrofit.flickrService.getResult(
            ConverterAPI.getOptionCallResult(
                DateUtils.getDateString(
                    calculatorDayWithSize(totalPhoto)
                ), totalPhotoOfPage.toInt(), 1
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
                    }
                }
            }

            override fun onFailure(call: Call<FlickrResult>, t: Throwable) {
                callBack.onFailure(call, t)
            }
        })
    }

    fun calculatorDayWithSize(totalPhoto: Int): Int {
        return ceil(totalPhoto / totalPhotoOfPage).toInt()
    }


    interface GetPhotoExploreCallBack {
        fun onSuccess(data: ArrayList<FlickrResult.Photos.Photo>)
        fun onFailure(call: Call<FlickrResult>, t: Throwable)
    }
}