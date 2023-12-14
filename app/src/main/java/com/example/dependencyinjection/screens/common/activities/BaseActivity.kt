package com.example.dependencyinjection.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.dependencyinjection.MyApplication
import com.example.dependencyinjection.common.dependencyInjection.ActivityCompositionRoot
import com.example.dependencyinjection.common.dependencyInjection.Injector
import com.example.dependencyinjection.common.dependencyInjection.PresentationCompositionRoot

open class BaseActivity : AppCompatActivity() {

    private val appCompositionRoot get() = (application as MyApplication).appCompositionRoot
    val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }

    private val compositionRoot by lazy {
        PresentationCompositionRoot(activityCompositionRoot)
    }

    protected val injector get() = Injector(compositionRoot)
}