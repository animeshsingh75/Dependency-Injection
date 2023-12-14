package com.example.dependencyinjection.common.dependencyInjection

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.dependencyinjection.screens.common.ScreensNavigator

class ActivityCompositionRoot(
    val activity: AppCompatActivity, private val appCompositionRoot: AppCompositionRoot
) {
    val application get() = activity.applicationContext
    val layoutInflater: LayoutInflater get() = LayoutInflater.from(activity)
    val fragmentManager get() = activity.supportFragmentManager

    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    val stackoverflowApi
        get() = appCompositionRoot.stackoverflowApi
}