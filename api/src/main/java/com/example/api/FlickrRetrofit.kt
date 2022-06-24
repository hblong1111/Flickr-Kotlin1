package com.example.api

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.create

import retrofit2.converter.gson.GsonConverterFactory

object FlickrRetrofit {
    init {
        createRetrofit()
        createService()
    }


    private lateinit var retrofit: Retrofit
    lateinit var flickrService: FlickrService


    private fun createRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://www.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createService() {
        flickrService = retrofit.create(FlickrService::class.java)
    }
}