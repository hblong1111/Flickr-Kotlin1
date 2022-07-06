package com.example.api

import android.text.TextUtils
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class FlickrResult {
    @SerializedName("photos")
    @Expose
    var photos: Photos? = null

    @SerializedName("extra")
    @Expose
    var extra: Extra? = null

    @SerializedName("stat")
    @Expose
    var stat: String? = null

    class Extra {
        @SerializedName("explore_date")
        @Expose
        var exploreDate: String? = null

        @SerializedName("next_prelude_interval")
        @Expose
        var nextPreludeInterval = 0
    }

    class Photos {
        @SerializedName("page")
        @Expose
        var page = 0

        @SerializedName("pages")
        @Expose
        var pages = 0

        @SerializedName("perpage")
        @Expose
        var perpage = 0

        @SerializedName("total")
        @Expose
        var total = 0

        @SerializedName("photo")
        @Expose
        var photo: List<Photo>? = null

        class Photo {
            @SerializedName("id")
            @Expose
            var id: String? = null

            @SerializedName("owner")
            @Expose
            var owner: String? = null

            @SerializedName("secret")
            @Expose
            var secret: String? = null

            @SerializedName("server")
            @Expose
            var server: String? = null

            @SerializedName("farm")
            @Expose
            var farm = 0

            @SerializedName("title")
            @Expose
            var title: String? = null

            @SerializedName("ispublic")
            @Expose
            var ispublic = 0

            @SerializedName("isfriend")
            @Expose
            var isfriend = 0

            @SerializedName("isfamily")
            @Expose
            var isfamily = 0

            @SerializedName("url_sq")
            @Expose
            var urlSq: String? = null

            @SerializedName("height_sq")
            @Expose
            var heightSq = 0

            @SerializedName("width_sq")
            @Expose
            var widthSq = 0

            @SerializedName("url_t")
            @Expose
            var urlT: String? = null

            @SerializedName("height_t")
            @Expose
            var heightT = 0

            @SerializedName("width_t")
            @Expose
            var widthT = 0

            @SerializedName("url_s")
            @Expose
            var urlS: String? = null

            @SerializedName("height_s")
            @Expose
            var heightS = 0

            @SerializedName("width_s")
            @Expose
            var widthS = 0

            @SerializedName("url_q")
            @Expose
            var urlQ: String? = null

            @SerializedName("height_q")
            @Expose
            var heightQ = 0

            @SerializedName("width_q")
            @Expose
            var widthQ = 0

            @SerializedName("url_m")
            @Expose
            var urlM: String? = null

            @SerializedName("height_m")
            @Expose
            var heightM: Int = 0

            @SerializedName("width_m")
            @Expose
            var widthM: Int = 0

            @SerializedName("url_n")
            @Expose
            var urlN: String? = null

            @SerializedName("height_n")
            @Expose
            var heightN = 0

            @SerializedName("width_n")
            @Expose
            var widthN = 0

            @SerializedName("url_z")
            @Expose
            var urlZ: String? = null

            @SerializedName("height_z")
            @Expose
            var heightZ = 0

            @SerializedName("width_z")
            @Expose
            var widthZ = 0

            @SerializedName("url_c")
            @Expose
            var urlC: String? = null

            @SerializedName("height_c")
            @Expose
            var heightC = 0

            @SerializedName("width_c")
            @Expose
            var widthC = 0

            @SerializedName("url_l")
            @Expose
            var urlL: String? = null

            @SerializedName("height_l")
            @Expose
            var heightL = 0

            @SerializedName("width_l")
            @Expose
            var widthL = 0

            @SerializedName("url_o")
            @Expose
            var urlO: String? = null

            @SerializedName("height_o")
            @Expose
            var heightO = 0

            @SerializedName("width_o")
            @Expose
            var widthO = 0


            fun getUrl(): String? {
                val url = when {
                    !TextUtils.isEmpty(urlM) -> urlM
                    !TextUtils.isEmpty(urlO) -> urlO
                    !TextUtils.isEmpty(urlL) -> urlL
                    !TextUtils.isEmpty(urlC) -> urlC
                    !TextUtils.isEmpty(urlZ) -> urlZ
                    !TextUtils.isEmpty(urlN) -> urlN
                    !TextUtils.isEmpty(urlQ) -> urlQ
                    !TextUtils.isEmpty(urlS) -> urlS
                    !TextUtils.isEmpty(urlSq) -> urlSq
                    !TextUtils.isEmpty(urlT) -> urlT
                    else -> {
                        null
                    }
                }

                return url
            }

            fun getHeight(): Int {


                val height = when {
                    !TextUtils.isEmpty(urlO) -> heightO
                    !TextUtils.isEmpty(urlL) -> heightL
                    !TextUtils.isEmpty(urlC) -> heightC
                    !TextUtils.isEmpty(urlZ) -> heightZ
                    !TextUtils.isEmpty(urlN) -> heightN
                    !TextUtils.isEmpty(urlM) -> heightM
                    !TextUtils.isEmpty(urlQ) -> heightQ
                    !TextUtils.isEmpty(urlS) -> heightS
                    !TextUtils.isEmpty(urlSq) -> heightSq
                    !TextUtils.isEmpty(urlT) -> heightT
                    else -> {
                        1
                    }
                }
                return height
            }

            fun getWidth(): Int {

                val width = when {
                    !TextUtils.isEmpty(urlO) -> widthO
                    !TextUtils.isEmpty(urlL) -> widthL
                    !TextUtils.isEmpty(urlC) -> widthC
                    !TextUtils.isEmpty(urlZ) -> widthZ
                    !TextUtils.isEmpty(urlN) -> widthN
                    !TextUtils.isEmpty(urlM) -> widthM
                    !TextUtils.isEmpty(urlQ) -> widthQ
                    !TextUtils.isEmpty(urlS) -> widthS
                    !TextUtils.isEmpty(urlSq) -> widthSq
                    !TextUtils.isEmpty(urlT) -> widthT
                    else -> {
                        1
                    }
                }
                return width
            }

            override fun equals(other: Any?): Boolean {
                if (other is Photo) {
                    return other.id.equals(id)
                }
                return false
            }

            override fun toString(): String {
                return "Photo(urlO=$urlO, url=${getUrl()})"
            }


        }
    }
}