package com.example.flickrkotlin.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object DateUtils {
    @SuppressLint("SimpleDateFormat")
    fun getDateString(day: Int = 0): String {
        val result: String
        val format = SimpleDateFormat("yyyy-MM-dd")
        val time = System.currentTimeMillis() - (day + 1) * 24 * 60 * 60 * 1000
        result = format.format(time)
        return result
    }

}