package com.example.dependencyinjection.common.dependencyInjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.dependencyinjection.screens.common.ScreensNavigatorImpl
import com.example.dependencyinjection.screens.common.viewsMvc.ScreensNavigator
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule {

    @Binds
    abstract fun screensNavigator(screensNavigatorImpl: ScreensNavigatorImpl):ScreensNavigator

    companion object{
        @Provides
        fun layoutInflater(activity: AppCompatActivity) = LayoutInflater.from(activity)
        @Provides
        fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager
    }
}