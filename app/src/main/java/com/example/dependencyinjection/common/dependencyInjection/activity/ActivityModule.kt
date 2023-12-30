package com.example.dependencyinjection.common.dependencyInjection.activity

import android.app.Activity
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.dependencyinjection.screens.common.ScreensNavigatorImpl
import com.example.dependencyinjection.screens.common.viewsMvc.ScreensNavigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
abstract class ActivityModule {

    @ActivityScoped
    @Binds
    abstract fun screensNavigator(screensNavigatorImpl: ScreensNavigatorImpl):ScreensNavigator

    companion object {

        @Provides
        fun appCompatActivity(activity: Activity) = activity as AppCompatActivity

        @Provides
        fun layoutInflater(activity: AppCompatActivity) = LayoutInflater.from(activity)

        @Provides
        fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager
    }
}