package com.haidev.radioartvisi.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.haidev.radioartvisi.R

@BindingAdapter("imageResource")
fun loadImage(view: ImageView, imageUrl: String) {
    if (imageUrl.isNotEmpty()) {
        val radius = view.context.resources.getDimensionPixelSize(R.dimen.dimen_small)
        val context = view.context
        Glide.with(context).load(imageUrl).transform(RoundedCorners(radius)).into(view)
    } else {
        val radius = view.context.resources.getDimensionPixelSize(R.dimen.dimen_small)
        val context = view.context
        Glide.with(context).load("https://i.ya-webdesign.com/images/no-logo-png-16.png")
            .transform(RoundedCorners(radius)).into(view)
    }

}