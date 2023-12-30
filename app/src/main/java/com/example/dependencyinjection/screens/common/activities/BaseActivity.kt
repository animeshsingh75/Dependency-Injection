package com.example.dependencyinjection.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.dependencyinjection.MyApplication
import com.example.dependencyinjection.common.dependencyInjection.presentation.PresentationModule

open class BaseActivity : AppCompatActivity() {

    private val appComponent get() = (application as MyApplication).appComponent
    val activityComponent by lazy {
        appComponent.newActivityComponentBuilder()
            .activity(this)
            .build()
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent(PresentationModule(this))
    }

    protected val injector get() = presentationComponent
}