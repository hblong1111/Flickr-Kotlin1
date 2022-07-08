package com.example.flickrkotlin.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build

class InternetUtils {

    companion object {
        fun checkConnection(context: Context): Boolean {
            val conMgr =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                conMgr.activeNetwork
            } else {
                conMgr.activeNetworkInfo
            }
            return netInfo != null
        }
    }
}