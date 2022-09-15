package com.nasa.gallery.utils

import android.content.Context
import android.graphics.Color
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.ImageLoader
import coil.request.ImageRequest

fun ImageView.loadImage(imageLoader: ImageLoader, url: String) {
    val request = ImageRequest.Builder(context)
        .target(this)
        .data(url)
        .placeholder(circularProgress(context))
        .error(com.google.android.material.R.drawable.mtrl_ic_error)
        .build()
    imageLoader.enqueue(request)
}

fun circularProgress(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 4.0F
        centerRadius = 20.0F
        setColorSchemeColors(Color.rgb(31, 9, 81), Color.rgb(3, 218, 197))
        start()
    }
}
