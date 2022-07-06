package com.example.flickrkotlin.binding

import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import androidx.compose.ui.unit.TextUnit
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {
    @BindingAdapter("app:loadUrl")
    @JvmStatic
    fun loadImageFromUrl(imageView: ImageView, url: String) {

        Log.d("longhb", "ImageBindingAdapter.loadImageFromUrl: $imageView ||| $url")
        Glide.with(imageView).load(url).into(imageView)
    }
}