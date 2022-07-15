package com.example.api.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class PhotoFavoriteResult {


    @SerializedName("photo")
    @Expose
    var photo: Photo? = null

    class Photo {
        @SerializedName("total")
        @Expose
        var total: Int? = null
    }

}