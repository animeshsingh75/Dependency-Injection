package com.example.dependencyinjection.common.service

import android.app.Service
import com.example.dependencyinjection.MyApplication
import com.example.dependencyinjection.common.dependencyInjection.service.ServiceModule

abstract class BaseService : Service() {

    private val appComponent get() = (application as MyApplication).appComponent

    val serviceComponent by lazy {
        appComponent.newServiceComponent(ServiceModule(this))
    }
}