package com.example.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PhotoInfoResult {

    @SerializedName("photo")
    @Expose
    var photo: Photo? = null

    @SerializedName("stat")
    @Expose
    var stat: String? = null

    class Photo {
        @SerializedName("id")
        @Expose
        var id: String? = null

        @SerializedName("dateuploaded")
        @Expose
        var dateuploaded: String? = null

        @SerializedName("owner")
        @Expose
        var owner: Owner? = null


        @SerializedName("description")
        @Expose
        var description: Description? = null

        @SerializedName("dates")
        @Expose
        var dates: Dates? = null

        @SerializedName("views")
        @Expose
        var views: String? = null

        @SerializedName("comments")
        @Expose
        var comments: Comments? = null

        class Comments {
            @SerializedName("_content")
            @Expose
            var content: String? = null
        }

        class Dates {
            @SerializedName("posted")
            @Expose
            var posted: String? = null

            @SerializedName("taken")
            @Expose
            var taken: String? = null

            @SerializedName("takengranularity")
            @Expose
            var takengranularity: Int? = null

            @SerializedName("takenunknown")
            @Expose
            var takenunknown: String? = null

            @SerializedName("lastupdate")
            @Expose
            var lastupdate: String? = null
        }


        class Description {
            @SerializedName("_content")
            @Expose
            var content: String? = null
        }

        class Owner {
            @SerializedName("nsid")
            @Expose
            var nsid: String? = null

            @SerializedName("username")
            @Expose
            var username: String? = null
        }
    }
}