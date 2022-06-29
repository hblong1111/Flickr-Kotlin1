package com.example.flickrkotlin.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBindingAdapter {


    @BindingAdapter("app:loadUrl")
    @JvmStatic
    fun loadImageFromUrl(imageView: ImageView, url: String) {
        Glide.with(imageView).load(url).into(imageView)
    }
}