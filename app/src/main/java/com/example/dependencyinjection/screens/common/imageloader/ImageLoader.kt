package com.example.dependencyinjection.screens.common.imageloader

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dependencyinjection.common.dependencyInjection.activity.ActivityScope
import javax.inject.Inject

@ActivityScope
class ImageLoader @Inject constructor(private val activity: AppCompatActivity) {

    private val requestOptions = RequestOptions.centerCropTransform()
    fun loadImage(imageUrl: String, target: ImageView) {
        Glide.with(activity)
            .load(imageUrl)
            .apply(requestOptions)
            .into(target)
    }
}