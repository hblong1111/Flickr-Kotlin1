package com.example.api

import com.example.api.model.CommentsPhotoResult
import com.example.api.model.FlickrResult
import com.example.api.model.PhotoFavoriteResult
import com.example.api.model.PhotoInfoResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface FlickrService {
    @GET("services/rest/")
    fun getResult(@QueryMap options: Map<String, String>): Call<FlickrResult>

    @GET("services/rest/")
    fun getInfo(@QueryMap options: Map<String, String>): Call<PhotoInfoResult>

    @GET("services/rest/")
    fun getFavorite(@QueryMap options: Map<String, String>): Call<PhotoFavoriteResult>

    @GET("services/rest/")
    fun getListCommentPhoto(@QueryMap options: Map<String, String>): Call<CommentsPhotoResult>
}