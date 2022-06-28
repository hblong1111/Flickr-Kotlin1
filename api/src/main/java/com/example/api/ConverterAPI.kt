package com.example.api

class ConverterAPI {
    companion object{
        fun getOptionCallResult(perPage: Int, page: Int): Map<String, String> {
            val result = mutableMapOf<String, String>()
            result["method"] = "flickr.interestingness.getList"
            result["api_key"] = "0bc8fffdaa47544a2b609502c1fb25ce"
            result["extras"] = "url_sq,url_t,url_s,url_q,url_m,url_n,url_z,url_c,url_l,url_o"
            result["per_page"] = "$perPage"
            result["page"] = "$page"
            result["format"] = "json"
            result["nojsoncallback"] = "1"
            return result
        }
    }

}