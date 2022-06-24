package com.example.api

import retrofit2.Call
import retrofit2.http.*


interface FlickrService {
    @GET("services/rest/")
    fun getResult(@QueryMap options: Map<String, String>): Call<FlickrResult>
}