package com.example.api.model

import android.text.TextUtils
import com.example.api.ConverterAPI
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.w3c.dom.Comment


class CommentsPhotoResult {

    @SerializedName("comments")
    @Expose
    var comments: Comments? = null

    class Comments {
        @SerializedName("photo_id")
        @Expose
        var photoId: String? = null

        @SerializedName("comment")
        @Expose
        var comment: List<Comment>? = null

        class Comment {


            @SerializedName("id")
            @Expose
            var id: String? = null

            @SerializedName("author")
            @Expose
            var author: String? = null

            @SerializedName("author_is_deleted")
            @Expose
            var authorIsDeleted: Int? = null

            @SerializedName("authorname")
            @Expose
            var authorname: String? = null

            @SerializedName("iconserver")
            @Expose
            var iconserver: String? = null

            @SerializedName("iconfarm")
            @Expose
            var iconfarm: Int? = null

            @SerializedName("datecreate")
            @Expose
            var datecreate: String? = null

            @SerializedName("permalink")
            @Expose
            var permalink: String? = null

            @SerializedName("path_alias")
            @Expose
            var pathAlias: Any? = null

            @SerializedName("realname")
            @Expose
            var realname: String? = null

            @SerializedName("_content")
            @Expose
            var content: String? = null


            fun getUsername(): String {
                return if (!TextUtils.isEmpty(realname)) {
                    realname!!
                } else {
                    authorname!!
                }
            }

            fun getUrlAvt(): String {
                return ConverterAPI.getUrlImageOwner(iconfarm!!, iconserver?.toInt()!!, author!!)
            }


            override fun equals(other: Any?): Boolean {
                return other is Comment && id.equals(other.id)
            }
        }
    }

}