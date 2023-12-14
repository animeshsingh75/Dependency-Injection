package com.example.dependencyinjection

import android.app.Application
import com.example.dependencyinjection.common.dependencyInjection.AppCompositionRoot

class MyApplication : Application() {

    lateinit var appCompositionRoot: AppCompositionRoot
    override fun onCreate() {
        super.onCreate()
        appCompositionRoot = AppCompositionRoot(this)
    }

}