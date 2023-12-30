package com.example.dependencyinjection.common.dependencyInjection.app

import com.example.dependencyinjection.common.dependencyInjection.activity.ActivityComponent
import com.example.dependencyinjection.common.dependencyInjection.activity.ActivityModule
import com.example.dependencyinjection.common.dependencyInjection.service.ServiceComponent
import com.example.dependencyinjection.common.dependencyInjection.service.ServiceModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun newActivityComponentBuilder(): ActivityComponent.Builder

    fun newServiceComponent(activityModule: ServiceModule): ServiceComponent
}