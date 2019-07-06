package com.example.musicapp.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

object ImageUtils {
    fun loadCircularImage(url: String, imageView: ImageView) {
        Picasso.get()
            .load(url)
            .fit()
            .transform(CircleTransform())
            .into(imageView)
    }
}